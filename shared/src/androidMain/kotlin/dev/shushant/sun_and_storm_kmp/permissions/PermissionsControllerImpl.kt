package dev.shushant.sun_and_storm_kmp.permissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import dev.shushant.sun_and_storm_kmp.permissions.manager.LocationPermissionManager
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.suspendCoroutine

class PermissionsControllerImpl(
    private val applicationContext: Context
) : PermissionsController {
    private val mutex: Mutex = Mutex()
    private val permissionResolver = PermissionResolver(applicationContext)
    override suspend fun providePermission(permission: Permission) {
        mutex.withLock {
            val platformPermission = permission.toPlatformPermission()
            val result = suspendCoroutine { continuation ->
                permissionResolver.requestPermission(
                    permission,
                    platformPermission
                ) { continuation.resumeWith(it) }
            }
            result.takeIf { it }?.let {
                processResult(permission)
            }
        }
    }

    private fun processResult(permission: Permission) = when (permission) {
        Permission.CAMERA -> LocationPermissionManager(applicationContext)
        Permission.GALLERY -> LocationPermissionManager(applicationContext)
        Permission.STORAGE -> LocationPermissionManager(applicationContext)
        Permission.WRITE_STORAGE -> LocationPermissionManager(applicationContext)
        Permission.LOCATION -> LocationPermissionManager(applicationContext)
        Permission.COARSE_LOCATION -> LocationPermissionManager(applicationContext)
        Permission.BLUETOOTH_LE -> LocationPermissionManager(applicationContext)
        Permission.REMOTE_NOTIFICATION -> LocationPermissionManager(applicationContext)
        Permission.RECORD_AUDIO -> LocationPermissionManager(applicationContext)
        Permission.BLUETOOTH_SCAN -> LocationPermissionManager(applicationContext)
        Permission.BLUETOOTH_ADVERTISE -> LocationPermissionManager(applicationContext)
        Permission.BLUETOOTH_CONNECT -> LocationPermissionManager(applicationContext)
    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean {
        return getPermissionState(permission) == PermissionState.Granted
    }

    @Suppress("ReturnCount")
    override suspend fun getPermissionState(permission: Permission): PermissionState {
        if (permission == Permission.REMOTE_NOTIFICATION &&
            Build.VERSION.SDK_INT in VERSIONS_WITHOUT_NOTIFICATION_PERMISSION
        ) {
            val isNotificationsEnabled = NotificationManagerCompat.from(applicationContext)
                .areNotificationsEnabled()
            return if (isNotificationsEnabled) {
                PermissionState.Granted
            } else {
                PermissionState.DeniedAlways
            }
        }
        val permissions: List<String> = permission.toPlatformPermission()
        val status: List<Int> = permissions.map {
            ContextCompat.checkSelfPermission(applicationContext, it)
        }
        val isAllGranted: Boolean = status.all { it == PackageManager.PERMISSION_GRANTED }
        if (isAllGranted) return PermissionState.Granted
        val isAllRequestRationale: Boolean = permissions.all {
            !permissionResolver.shouldShowRequestPermissionRationale(it)
        }
        return if (isAllRequestRationale) PermissionState.NotDetermined
        else PermissionState.Denied
    }

    override fun openAppSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", applicationContext.packageName, null)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        applicationContext.startActivity(intent)
    }


    override fun onDataReceived(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun Permission.toPlatformPermission(): List<String> {
        return when (this) {
            Permission.CAMERA -> listOf(Manifest.permission.CAMERA)
            Permission.GALLERY -> listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            Permission.STORAGE -> listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            Permission.WRITE_STORAGE -> listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            Permission.LOCATION -> listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            Permission.COARSE_LOCATION -> listOf(Manifest.permission.ACCESS_COARSE_LOCATION)
            Permission.REMOTE_NOTIFICATION -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    listOf(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    emptyList()
                }
            }

            Permission.RECORD_AUDIO -> listOf(Manifest.permission.RECORD_AUDIO)
            Permission.BLUETOOTH_LE -> allBluetoothPermissions()
            Permission.BLUETOOTH_SCAN -> bluetoothScanCompat()
            Permission.BLUETOOTH_ADVERTISE -> bluetoothAdvertiseCompat()
            Permission.BLUETOOTH_CONNECT -> bluetoothConnectCompat()
        }
    }

    /**
     * @see https://developer.android.com/guide/topics/connectivity/bluetooth/permissions
     */
    private fun allBluetoothPermissions() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_ADVERTISE,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }

    private fun bluetoothScanCompat() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(Manifest.permission.BLUETOOTH_SCAN)
        } else {
            listOf(Manifest.permission.BLUETOOTH)
        }

    private fun bluetoothAdvertiseCompat() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(Manifest.permission.BLUETOOTH_ADVERTISE)
        } else {
            listOf(Manifest.permission.BLUETOOTH)
        }

    private fun bluetoothConnectCompat() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(Manifest.permission.BLUETOOTH_CONNECT)
        } else {
            listOf(Manifest.permission.BLUETOOTH)
        }

    private companion object {
        val VERSIONS_WITHOUT_NOTIFICATION_PERMISSION =
            Build.VERSION_CODES.KITKAT until Build.VERSION_CODES.TIRAMISU
    }

}
