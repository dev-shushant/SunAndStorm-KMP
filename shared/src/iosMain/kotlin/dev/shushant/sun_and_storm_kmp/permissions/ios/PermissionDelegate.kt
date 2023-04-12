package dev.shushant.sun_and_storm_kmp.permissions.ios

import dev.shushant.sun_and_storm_kmp.permissions.PermissionState


internal interface PermissionDelegate {
    suspend fun providePermission()
    suspend fun getPermissionState(): PermissionState

}
