package dev.shushant.sun_and_storm_kmp.permissions

actual interface PermissionsController {

    actual suspend fun providePermission(permission: Permission)


    actual suspend fun isPermissionGranted(permission: Permission): Boolean


    actual suspend fun getPermissionState(permission: Permission): PermissionState


    actual fun openAppSettings()

    fun invoke(): PermissionsController = PermissionsControllerImpl()

}