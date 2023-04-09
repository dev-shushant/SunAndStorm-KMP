package dev.shushant.sun_and_storm_kmp.location

import dev.shushant.sun_and_storm_kmp.location.extension.previousAuthorizationStatus
import dev.shushant.sun_and_storm_kmp.location.extension.requiredPermission
import kotlinx.cinterop.useContents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import platform.CoreLocation.*
import platform.darwin.NSObject

internal class CLLocationManagerDelegate : NSObject(), CLLocationManagerDelegateProtocol {

    // -------------------------------------------------------------------------------------------
    //  Implementation of CLLocationManagerDelegateProtocol
    // -------------------------------------------------------------------------------------------
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) =
        notifyDidChangeAuthorization(
            LocationAuthorizationStatus.fromInt(manager.authorizationStatus)
        )

    override fun locationManager(
        manager: CLLocationManager,
        didChangeAuthorizationStatus: CLAuthorizationStatus
    ) =
        notifyDidChangeAuthorization(
            LocationAuthorizationStatus.fromInt(didChangeAuthorizationStatus)
        )

    private fun notifyDidChangeAuthorization(status: LocationAuthorizationStatus) {
        val isGranted = SunAndStormLocation.requiredPermission == status ||
                LocationAuthorizationStatus.AuthorizedAlways == status
        SunAndStormLocation.notifyOnPermissionUpdated(isGranted)
        SunAndStormLocation.previousAuthorizationStatus = status
        coroutineScope.launch {  SunAndStormLocation.startLocationUpdating() }
    }

    @Suppress("CONFLICTING_OVERLOADS")
    override fun locationManager(
        manager: CLLocationManager,
        didStartMonitoringForRegion: CLRegion
    ) {
    }

    @Suppress("CONFLICTING_OVERLOADS")
    override fun locationManager(manager: CLLocationManager, didEnterRegion: CLRegion) {
    }

    @Suppress("CONFLICTING_OVERLOADS")
    override fun locationManager(manager: CLLocationManager, didExitRegion: CLRegion) {
    }

    override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) =
        notify(didUpdateLocations.last() as? CLLocation, manager.heading)

    override fun locationManager(manager: CLLocationManager, didUpdateHeading: CLHeading) =
        notify(manager.location, didUpdateHeading)

    // -------------------------------------------------------------------------------------------
    //  Private
    // -------------------------------------------------------------------------------------------

    private fun notify(lastLocation: CLLocation?, lastHeading: CLHeading?) {
        val location = lastLocation ?: return
        val heading = lastHeading?.trueHeading ?: 0.0
        location.coordinate.useContents {
            val geoCoder = CLGeocoder()
            //var address = ""
            geoCoder.reverseGeocodeLocation(location) { placemarks, error ->
                if (error != null){
                    print("reverse geodcode fail: ${error.localizedDescription}")
                }
                val placemark = placemarks as? List<CLPlacemark>
                val address = Address()
                placemark.takeIf { it?.isNotEmpty()  == true}?.let {
                    val pm = it[0]
                    if (pm.subLocality != null) {
                        address.subLocality = pm.subLocality?:""
                    }
                    if (pm.thoroughfare != null) {
                        address.thoroughfare = pm.thoroughfare?:""
                    }
                    if (pm.locality != null) {
                        address.locality = pm.locality?:""
                    }
                    if (pm.country != null) {
                        address.country = pm.country?:""
                    }
                    if (pm.postalCode != null) {
                        address.postalCode = pm.postalCode?:""
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
                SunAndStormLocation.notifyOnLocationUpdated(data)
            }
        }
    }
}