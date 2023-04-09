package dev.shushant.sun_and_storm_kmp.location

typealias OnLocationUpdatedBlock = (location: LocationData) -> Unit
typealias OnLocationUnavailableBlock = () -> Unit
typealias OnPermissionUpdatedBlock = (isGranted: Boolean) -> Unit

interface SunAndStormLocationCompanion {
    fun isPermissionAllowed(): Boolean
    fun currentLocation(block: OnLocationUpdatedBlock)
    fun requestPermission()
    suspend fun startLocationUpdating()
    fun stopLocationUpdating()

    fun onLocationUnavailable(target: Any, block: OnLocationUnavailableBlock): SunAndStormLocationCompanion
    fun onLocationUpdated(target: Any, block: OnLocationUpdatedBlock): SunAndStormLocationCompanion
    fun onPermissionUpdated(target: Any, block: OnPermissionUpdatedBlock): SunAndStormLocationCompanion

    fun removeAllListeners()
    fun removeListeners(target: Any)
    fun removeOnLocationUnavailable(target: Any)
    fun removeOnLocationUpdated(target: Any)
    fun removeOnPermissionUpdated(target: Any)
}