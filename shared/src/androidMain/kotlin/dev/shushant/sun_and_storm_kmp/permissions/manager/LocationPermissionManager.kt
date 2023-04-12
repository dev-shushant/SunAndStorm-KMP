package dev.shushant.sun_and_storm_kmp.permissions.manager

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dev.shushant.sun_and_storm_kmp.permissions.data.Address
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates
import dev.shushant.sun_and_storm_kmp.permissions.data.LocationData
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Locale

class LocationPermissionManager(applicationContext: Context) : KoinComponent {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val appSettings: AppSettings by inject()
    private lateinit var locationCallback: LocationCallback

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
    }

}