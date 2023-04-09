package dev.shushant.sun_and_storm_kmp.location.extension


import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocationCompanion
import dev.shushant.sun_and_storm_kmp.location.LocationAuthorizationStatus

typealias OnAlwaysAllowsPermissionRequiredBlock = () -> Unit

var SunAndStormLocation.Companion.requiredPermission: LocationAuthorizationStatus
    get() = locationManager.requiredPermission.value
    set(value) { locationManager.requiredPermission.value = value }

fun SunAndStormLocation.Companion.onAlwaysAllowsPermissionRequired(
    target: Any,
    block: OnAlwaysAllowsPermissionRequiredBlock
): SunAndStormLocationCompanion {
    locationManager.onAlwaysAllowsPermissionRequired(target, block)
    return this
}

fun SunAndStormLocation.Companion.removeOnAlwaysAllowsPermissionRequired(target: Any) =
    locationManager.removeOnAlwaysAllowsPermissionRequired(target)
    
internal var SunAndStormLocation.Companion.previousAuthorizationStatus: LocationAuthorizationStatus
    get() = locationManager.previousAuthorizationStatus.value
    set(value) { locationManager.previousAuthorizationStatus.value = value }
