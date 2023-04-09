package dev.shushant.sun_and_storm_kmp.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.CancellationTokenSource
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation.Companion.notifyOnLocationUnavailable
import dev.shushant.sun_and_storm_kmp.location.extension.toLocationData
import dev.shushant.sun_and_storm_kmp.location.observers.ActivityLifecycleObserver
import dev.shushant.sun_and_storm_kmp.location.utils.LocationUtil
import java.lang.ref.WeakReference
import java.util.Locale


internal actual class LocationManager {

    // -------------------------------------------------------------------------------------------
    //  Public (Actual)
    // -------------------------------------------------------------------------------------------

    actual fun isPermissionAllowed() = focusedActivity?.let {
        ActivityCompat.checkSelfPermission(
            it,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    } ?: false

    actual fun removeAllListeners() {}

    actual fun removeListeners(target: Any) {}

    actual fun requestPermission() {
        val activity = focusedActivity ?: run {
            notifyOnLocationUnavailable()
            return
        }

        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            requestPermissionsRequestCode
        )
    }

    @SuppressLint("MissingPermission")
    actual fun startLocationUpdating() {
        val activity = focusedActivity ?: run {
            notifyOnLocationUnavailable()
            return
        }

        if (!isPermissionAllowed()) {
            requestPermission()
            notifyOnLocationUnavailable()
        } else if (!LocationUtil.checkLocationEnable(activity)) {
            notifyOnLocationUnavailable()
        } else {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }

    }

    actual fun stopLocationUpdating() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    //https://developer.android.com/training/location/retrieve-current?hl=ko#BestEstimate
    @SuppressLint("MissingPermission")
    actual fun getCurrentLocation() {
        val activity = focusedActivity ?: run {
            notifyOnLocationUnavailable()
            return
        }

        var isLocationNotified = false

        if (!isPermissionAllowed()) {
            requestPermission()
            notifyOnLocationUnavailable()
        } else if (!LocationUtil.checkLocationEnable(activity)) {
            notifyOnLocationUnavailable()
        } else {

            val cts = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                cts.token
            ).addOnSuccessListener { location ->
                if (location != null) {
                    val geocoder = Geocoder(focusedActivity!!, Locale.getDefault())
                    val addresses: MutableList<Address>? =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    val address = Address(
                        country = addresses?.get(0)?.countryName ?: "",
                        locality = addresses?.get(0)?.locality ?: "",
                        subLocality = addresses?.get(0)?.subLocality ?: "",
                        state = addresses?.get(0)?.adminArea ?: "",
                        postalCode = addresses?.get(0)?.postalCode ?: ""
                    )
                    SunAndStormLocation.notifyOnLocationUpdated(location.toLocationData(address))
                    isLocationNotified = true

                    // For update latest location
                    fusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.getMainLooper()
                    )
                }

            }.addOnFailureListener {}

            Handler(Looper.getMainLooper()).postDelayed({
                if (!isLocationNotified) {
                    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                        if (location != null) {
                            val geocoder = Geocoder(focusedActivity!!, Locale.getDefault())
                            val addresses: MutableList<Address>? =
                                geocoder.getFromLocation(location.latitude, location.longitude, 1)
                            val address = Address(
                                country = addresses?.get(0)?.countryName ?: "",
                                locality = addresses?.get(0)?.locality ?: "",
                                subLocality = addresses?.get(0)?.subLocality ?: "",
                                state = addresses?.get(0)?.adminArea ?: "",
                                postalCode = addresses?.get(0)?.postalCode ?: ""
                            )
                            SunAndStormLocation.notifyOnLocationUpdated(location.toLocationData(address))
                            isLocationNotified = true

                            // For update latest location
                            fusedLocationClient.requestLocationUpdates(
                                locationRequest,
                                locationCallback,
                                Looper.getMainLooper()
                            )
                        }
                    }.addOnFailureListener {}
                }
            }, (5 * 1000).toLong())

            Handler(Looper.getMainLooper()).postDelayed({
                if (!isLocationNotified) {
                    notifyOnLocationUnavailable()
                }
            }, (10 * 1000).toLong())

        }

    }

    // -------------------------------------------------------------------------------------------
    //  Internal
    // -------------------------------------------------------------------------------------------

    internal var activity: WeakReference<Activity>? = null

    internal fun configure(context: Context) {
        val application = context.applicationContext as? Application
        application?.registerActivityLifecycleCallbacks(ActivityLifecycleObserver) ?: run {
            val activity = context.applicationContext as? Activity
            this.activity = WeakReference(activity)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val location = locationResult.locations?.last() ?: return
                val coordinates = Coordinates(location.latitude, location.longitude)
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses: MutableList<Address>? =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)
                val data = LocationData(
                    location.accuracy.toDouble(),
                    location.altitude,
                    0.0,
                    location.bearing.toDouble(),
                    location.speed.toDouble(),
                    coordinates,
                    address = Address(
                        country = addresses?.get(0)?.countryName ?: "",
                        locality = addresses?.get(0)?.locality ?: "",
                        subLocality = addresses?.get(0)?.subLocality ?: "",
                        state = addresses?.get(0)?.adminArea ?: "",
                        postalCode = addresses?.get(0)?.postalCode ?: ""
                    )
                )
                SunAndStormLocation.notifyOnLocationUpdated(data)
            }
            // part Device at onLocationAvailability I don't use it because I don't believe the value of
        }

        val settings = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        LocationServices
            .getSettingsClient(context)
            .checkLocationSettings(settings)
    }

    internal fun processRequestPermissionsResult(
        requestCode: Int,
        @Suppress("UNUSED_PARAMETER")
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            requestPermissionsRequestCode -> {
                if (grantResults.isEmpty()) {
                    return
                }
                when (grantResults[0]) {
                    PackageManager.PERMISSION_GRANTED -> {
                        startLocationUpdating()
                        SunAndStormLocation.notifyOnPermissionUpdated(true)
                    }

                    PackageManager.PERMISSION_DENIED ->
                        SunAndStormLocation.notifyOnPermissionUpdated(false)

                    else -> Unit
                }
            }
        }
    }

    internal fun showNotificationSetting() {
        val activity = focusedActivity ?: run {
            notifyOnLocationUnavailable()
            return
        }

        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                data = Uri.fromParts("package", activity.applicationInfo.packageName, null)
                activity.startActivity(this)
            }
    }

    // -------------------------------------------------------------------------------------------
    //  Private
    // -------------------------------------------------------------------------------------------

    private val requestPermissionsRequestCode = 4885

    private val focusedActivity: Activity?
        get() = activity?.get()?.let {
            if (it.isFinishing || it.isDestroyed) null else {
                it
            }
        }

    @SuppressLint("StaticFieldLeak")
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var locationCallback: LocationCallback
    private var locationRequest: LocationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        fastestInterval = 1 * 1000L
        interval = 10 * 1000L
    }

    fun setLocationRequest(sunAndStormLocationRequest: SunAndStormLocationRequest) {
        locationRequest = LocationRequest.create().apply {
            priority = sunAndStormLocationRequest.priority.value
            sunAndStormLocationRequest.fastestInterval?.let { fastestInterval = it }
            sunAndStormLocationRequest.interval?.let { interval = it }
            sunAndStormLocationRequest.maxWaitTime?.let { maxWaitTime = it }
            sunAndStormLocationRequest.smallestDisplacement?.let { smallestDisplacement = it }
            sunAndStormLocationRequest.isWaitForAccurateLocation?.let { isWaitForAccurateLocation = it }
            sunAndStormLocationRequest.numUpdates?.let { numUpdates = it }
            sunAndStormLocationRequest.expirationTime?.let { expirationTime = it }
        }
    }
}