package dev.shushant.sun_and_storm_kmp.permissions

import dev.shushant.sun_and_storm_kmp.permissions.data.Address
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates
import dev.shushant.sun_and_storm_kmp.permissions.data.LocationData
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLHeading
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.CLPlacemark
import platform.darwin.NSObject

internal class LocationManagerDelegate(val appSettings: AppSettings) : NSObject(),
    CLLocationManagerDelegateProtocol {
    private var callback: ((CLAuthorizationStatus) -> Unit)? = null


    private val locationManager = CLLocationManager()

    init {
        locationManager.delegate = this
    }

    fun requestLocationAccess(callback: (CLAuthorizationStatus) -> Unit) {
        this.callback = callback
        locationManager.requestWhenInUseAuthorization()
    }

    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) {
        callback?.invoke(didChangeAuthorizationStatus)
        callback = null
    }

    override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
        callback?.invoke(manager.authorizationStatus)
        callback = null
    }

    override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) =
        notify(didUpdateLocations.last() as? CLLocation, manager.heading)

    override fun locationManager(manager: CLLocationManager, didUpdateHeading: CLHeading) =
        notify(manager.location, didUpdateHeading)


    private fun notify(lastLocation: CLLocation?, lastHeading: CLHeading?) {
        val location = lastLocation ?: return
        val heading = lastHeading?.trueHeading ?: 0.0
        location.coordinate.useContents {
            val geoCoder = CLGeocoder()
            //var address = ""
            geoCoder.reverseGeocodeLocation(location) { placemarks, error ->
                if (error != null) {
                    print("reverse geodcode fail: ${error.localizedDescription}")
                }
                val placemark = placemarks as? List<CLPlacemark>
                val address = Address()
                placemark.takeIf { it?.isNotEmpty() == true }?.let {
                    val pm = it[0]
                    if (pm.subLocality != null) {
                        address.subLocality = pm.subLocality ?: ""
                    }
                    if (pm.thoroughfare != null) {
                        address.thoroughfare = pm.thoroughfare ?: ""
                    }
                    if (pm.locality != null) {
                        address.locality = pm.locality ?: ""
                    }
                    if (pm.country != null) {
                        address.country = pm.country ?: ""
                    }
                    if (pm.postalCode != null) {
                        address.postalCode = pm.postalCode ?: ""
                    }
                }
                val coordinates = Coordinates(
                    latitude,
                    longitude
                )
                val data = LocationData(
                    location.horizontalAccuracy,
                    location.altitude,
                    location.verticalAccuracy,
                    heading,
                    location.speed,
                    coordinates,
                    address = address
                )
                appSettings.updateLocationData(data)
            }
        }
    }

    fun startUpdating() {
        if (!locationManager.locationServicesEnabled) {
            return
        }
        locationManager.startUpdatingHeading()
        locationManager.startUpdatingLocation()
    }
}
