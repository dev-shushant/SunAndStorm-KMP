package dev.shushant.sun_and_storm_kmp.location

import dev.shushant.sun_and_storm_kmp.location.extension.OnAlwaysAllowsPermissionRequiredBlock
import dev.shushant.sun_and_storm_kmp.location.native.NativeAtomicReference
import dev.shushant.sun_and_storm_kmp.location.extension.appending
import dev.shushant.sun_and_storm_kmp.location.extension.removed
import dev.shushant.sun_and_storm_kmp.location.utils.Version
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLLocationAccuracyBestForNavigation
import platform.UIKit.UIDevice

internal actual class LocationManager {

    // -------------------------------------------------------------------------------------------
    //  Public (Actual)
    // -------------------------------------------------------------------------------------------
    private val scope = CoroutineScope(Dispatchers.Main)
    actual fun isPermissionAllowed() =
        authorizationStatus == requiredPermission.value

    actual fun removeAllListeners() {
        onAlwaysAllowsPermissionRequiredBlockMap.value = emptyMap()
    }

    actual fun removeListeners(target: Any) =
        removeOnAlwaysAllowsPermissionRequired(target)

    actual fun requestPermission() = if (isRequiredAllowAlways) {
        locationManager.requestAlwaysAuthorization()
        locationManager.allowsBackgroundLocationUpdates = true
        locationManager.pausesLocationUpdatesAutomatically = false
    } else {
        locationManager.requestWhenInUseAuthorization()
    }

    actual fun startLocationUpdating() = when (authorizationStatus) {
        LocationAuthorizationStatus.AuthorizedAlways -> startUpdating()
        LocationAuthorizationStatus.AuthorizedWhenInUse -> if (isRequiredAllowAlways) {
            if (previousAuthorizationStatus.value == LocationAuthorizationStatus.AuthorizedWhenInUse) {
                notifyOnAlwaysAllowsPermissionRequired()
            } else {
                requestPermission()
            }
        } else {
            startUpdating()
        }

        LocationAuthorizationStatus.Denied -> SunAndStormLocation.notifyOnLocationUnavailable()
        else -> requestPermission()
    }

    actual fun stopLocationUpdating() {
        scope.launch {
            if (!locationManager.locationServicesEnabled) {
                return@launch
            }
            locationManager.stopUpdatingHeading()
            locationManager.stopUpdatingLocation()
        }
    }

    //same operation with startLocationUpdating()
    actual fun getCurrentLocation() = when (authorizationStatus) {
        LocationAuthorizationStatus.AuthorizedAlways -> startUpdating()
        LocationAuthorizationStatus.AuthorizedWhenInUse -> if (isRequiredAllowAlways) {
            if (previousAuthorizationStatus.value == LocationAuthorizationStatus.AuthorizedWhenInUse) {
                notifyOnAlwaysAllowsPermissionRequired()
            } else {
                requestPermission()
            }
        } else {
            startUpdating()
        }

        LocationAuthorizationStatus.Denied -> SunAndStormLocation.notifyOnLocationUnavailable()
        else -> requestPermission()
    }

    // -------------------------------------------------------------------------------------------
    //  Public
    // -------------------------------------------------------------------------------------------

    val requiredPermission = NativeAtomicReference(LocationAuthorizationStatus.AuthorizedAlways)
    val previousAuthorizationStatus = NativeAtomicReference(LocationAuthorizationStatus.NotSet)

    fun onAlwaysAllowsPermissionRequired(
        target: Any,
        block: OnAlwaysAllowsPermissionRequiredBlock
    ) {
        onAlwaysAllowsPermissionRequiredBlockMap.value =
            onAlwaysAllowsPermissionRequiredBlockMap.value.appending(target, block)
    }

    fun removeOnAlwaysAllowsPermissionRequired(target: Any) {
        onAlwaysAllowsPermissionRequiredBlockMap.value =
            onAlwaysAllowsPermissionRequiredBlockMap.value.removed(target)
    }

    // -------------------------------------------------------------------------------------------
    //  Private
    // -------------------------------------------------------------------------------------------

    private val isRequiredAllowAlways: Boolean
        get() = requiredPermission.value == LocationAuthorizationStatus.AuthorizedAlways

    private val authorizationStatus: LocationAuthorizationStatus
        get() = if (Version(UIDevice.currentDevice.systemVersion) >= Version("14")) {
            LocationAuthorizationStatus.fromInt(CLLocationManager().authorizationStatus)
        } else {
            LocationAuthorizationStatus.fromInt(CLLocationManager.authorizationStatus())
        }

    private val locationManager: CLLocationManager by lazy {
        val manager = CLLocationManager()
        manager.desiredAccuracy = kCLLocationAccuracyBestForNavigation
        manager.setDelegate(locationDelegate)
        manager
    }

    private val locationDelegate: CLLocationManagerDelegate by lazy {
        CLLocationManagerDelegate()
    }

    private val onAlwaysAllowsPermissionRequiredBlockMap =
        NativeAtomicReference(mapOf<Any, OnAlwaysAllowsPermissionRequiredBlock>())

    private fun notifyOnAlwaysAllowsPermissionRequired() {
        onAlwaysAllowsPermissionRequiredBlockMap.value.forEach { it.value() }
    }

    private fun startUpdating() {
        scope.launch {
            if (!locationManager.locationServicesEnabled) {
                return@launch
            }
            locationManager.startUpdatingHeading()
            locationManager.startUpdatingLocation()
        }
    }
}
