package dev.shushant.sun_and_storm_kmp.permissions

import android.app.Activity

actual interface PermissionsController {
    actual suspend fun providePermission(permission: Permission)
    actual suspend fun isPermissionGranted(permission: Permission): Boolean
    actual suspend fun getPermissionState(permission: Permission): PermissionState
    actual fun openAppSettings()

    fun onDataReceived(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    )

    companion object {
        operator fun invoke(
            applicationContext: Activity
        ): PermissionsController {
            return PermissionsControllerImpl(
                applicationContext = applicationContext
            )
        }
    }
}
