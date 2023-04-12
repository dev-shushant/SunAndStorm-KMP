package dev.shushant.sun_and_storm_kmp.permissions.ios

import dev.shushant.sun_and_storm_kmp.permissions.DeniedAlwaysException
import dev.shushant.sun_and_storm_kmp.permissions.LocationManagerDelegate
import dev.shushant.sun_and_storm_kmp.permissions.Permission
import dev.shushant.sun_and_storm_kmp.permissions.PermissionState
import platform.CoreLocation.CLAuthorizationStatus
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusNotDetermined
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class LocationPermissionDelegate(
    private val locationManagerDelegate: LocationManagerDelegate,
    private val permission: Permission
) : PermissionDelegate {
    override suspend fun providePermission() {
        return provideLocationPermission(CLLocationManager.authorizationStatus())
    }

    override suspend fun getPermissionState(): PermissionState {
        val status: CLAuthorizationStatus = CLLocationManager.authorizationStatus()
        return when (status) {
            kCLAuthorizationStatusAuthorizedAlways,
            kCLAuthorizationStatusAuthorizedWhenInUse -> PermissionState.Granted

            kCLAuthorizationStatusNotDetermined -> PermissionState.NotDetermined
            kCLAuthorizationStatusDenied -> PermissionState.DeniedAlways
            else -> error("unknown location authorization status $status")
        }
    }

    private suspend fun provideLocationPermission(
        status: CLAuthorizationStatus
    ) {
        when (status) {
            kCLAuthorizationStatusAuthorizedAlways,
            kCLAuthorizationStatusAuthorizedWhenInUse -> {
                locationManagerDelegate.startUpdating()
            }

            kCLAuthorizationStatusNotDetermined -> {
                val newStatus = suspendCoroutine { continuation ->
                    locationManagerDelegate.requestLocationAccess { continuation.resume(it) }
                }
                provideLocationPermission(newStatus)
            }

            kCLAuthorizationStatusDenied -> throw DeniedAlwaysException(permission)
            else -> error("unknown location authorization status $status")
        }
    }
}
