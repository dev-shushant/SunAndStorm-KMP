package dev.shushant.permissionframework.permissions.ios

import dev.shushant.permissionframework.permissions.LocationManagerDelegate
import dev.shushant.permissionframework.permissions.Permission
import dev.shushant.permissionframework.permissions.PermissionState
import dev.shushant.sun_and_storm_kmp.permissions.ios.PermissionsControllerProtocol
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.AVFoundation.AVMediaTypeAudio
import platform.AVFoundation.AVMediaTypeVideo
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

internal class PermissionsController : PermissionsControllerProtocol, KoinComponent {
    private val appSettings: AppSettings by inject()
    private val locationManagerDelegate = LocationManagerDelegate(appSettings)

    override suspend fun providePermission(permission: Permission) {
        return getDelegate(permission).providePermission()
    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean {
        return getDelegate(permission).getPermissionState() == PermissionState.Granted
    }

    override suspend fun getPermissionState(permission: Permission): PermissionState {
        return getDelegate(permission).getPermissionState()
    }

    override fun openAppSettings() {
        val settingsUrl: NSURL = NSURL.URLWithString(UIApplicationOpenSettingsURLString)!!
        UIApplication.sharedApplication.openURL(settingsUrl)
    }

    private fun getDelegate(permission: Permission): PermissionDelegate {
        return when (permission) {
            Permission.REMOTE_NOTIFICATION -> RemoteNotificationPermissionDelegate()
            Permission.CAMERA -> AVCapturePermissionDelegate(AVMediaTypeVideo, permission)
            Permission.GALLERY -> GalleryPermissionDelegate()
            Permission.STORAGE, Permission.WRITE_STORAGE -> AlwaysGrantedPermissionDelegate()
            Permission.LOCATION, Permission.COARSE_LOCATION ->
                LocationPermissionDelegate(locationManagerDelegate, permission)

            Permission.RECORD_AUDIO -> AVCapturePermissionDelegate(AVMediaTypeAudio, permission)
            Permission.BLUETOOTH_LE, Permission.BLUETOOTH_SCAN,
            Permission.BLUETOOTH_ADVERTISE, Permission.BLUETOOTH_CONNECT ->
                BluetoothPermissionDelegate(permission)
        }
    }
}
