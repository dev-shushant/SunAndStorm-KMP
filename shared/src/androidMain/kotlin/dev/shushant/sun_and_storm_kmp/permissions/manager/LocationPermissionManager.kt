package dev.shushant.sun_and_storm_kmp.permissions.manager

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Looper
import co.touchlab.kermit.Logger
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.*
import com.google.android.gms.tasks.CancellationTokenSource
import dev.shushant.sun_and_storm_kmp.permissions.data.Address
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates
import dev.shushant.sun_and_storm_kmp.permissions.data.LocationData
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Locale

class LocationPermissionManager(val applicationContext: Context) : KoinComponent {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val appSettings: AppSettings by inject()
    private lateinit var locationCallback: LocationCallback
    private val looper = Looper.getMainLooper()

    private var locationRequest: LocationRequest =
        LocationRequest.Builder(PRIORITY_HIGH_ACCURACY, 1000L)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(1000L)
            .setMaxUpdateDelayMillis(5 * 1000L)
            .setMaxUpdates(2)
            .build()

    init {
        configure(applicationContext)
    }

    private fun configure(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val location = locationResult.locations.last() ?: return
                val coordinates = Coordinates(location.latitude, location.longitude)
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses =
                    geocoder.getFromLocation(/* latitude = */ location.latitude, /* longitude = */
                        location.longitude, /* maxResults = */
                        1
                    )
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
                appSettings.updateLocationData(data)
            }
        }

        val settings = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        LocationServices
            .getSettingsClient(context)
            .checkLocationSettings(settings)

        startUpdating()
    }

    @SuppressLint("MissingPermission")
    fun startUpdating() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            looper
        )
       // currentLocation()
    }

    fun stopLocationUpdating() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        looper.quitSafely()
    }

    @SuppressLint("MissingPermission")
    fun currentLocation() {
        val cts = CancellationTokenSource()
        fusedLocationClient.getCurrentLocation(
            PRIORITY_HIGH_ACCURACY,
            cts.token
        ).addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(applicationContext, Locale.getDefault())
                val addresses: MutableList<android.location.Address>? =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)
                val coordinates = Coordinates(location.latitude, location.longitude)
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
                appSettings.updateLocationData(data)
                // For update latest location
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    looper
                )
            }

        }.addOnFailureListener {
            Logger.d {
                it.localizedMessage ?: ""
            }
        }
    }

}