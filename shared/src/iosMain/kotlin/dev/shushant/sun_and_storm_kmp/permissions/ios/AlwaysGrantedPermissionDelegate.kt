package dev.shushant.sun_and_storm_kmp.permissions.ios

import dev.shushant.sun_and_storm_kmp.permissions.PermissionState

internal class AlwaysGrantedPermissionDelegate : PermissionDelegate {
    override suspend fun providePermission() = Unit

    override suspend fun getPermissionState(): PermissionState = PermissionState.Granted
}
