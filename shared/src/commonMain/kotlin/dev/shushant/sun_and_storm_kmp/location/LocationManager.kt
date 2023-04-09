package dev.shushant.sun_and_storm_kmp.location

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect class LocationManager() {
    fun isPermissionAllowed(): Boolean
    fun removeAllListeners()
    fun removeListeners(target: Any)
    fun requestPermission()
    fun startLocationUpdating()
    fun stopLocationUpdating()
    fun getCurrentLocation()
}