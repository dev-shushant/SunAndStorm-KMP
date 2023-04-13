package dev.shushant.permissionframework.permissions.ios

import dev.shushant.permissionframework.permissions.PermissionState


internal interface PermissionDelegate {
    suspend fun providePermission()
    suspend fun getPermissionState(): PermissionState

}
