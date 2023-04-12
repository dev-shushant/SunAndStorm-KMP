package dev.shushant.sun_and_storm_kmp.permissions.ios

import dev.shushant.sun_and_storm_kmp.permissions.Permission
import dev.shushant.sun_and_storm_kmp.permissions.PermissionState

interface PermissionsControllerProtocol  {
    suspend fun providePermission(permission: Permission)
    suspend fun isPermissionGranted(permission: Permission): Boolean
    suspend fun getPermissionState(permission: Permission): PermissionState
    fun openAppSettings()
}
