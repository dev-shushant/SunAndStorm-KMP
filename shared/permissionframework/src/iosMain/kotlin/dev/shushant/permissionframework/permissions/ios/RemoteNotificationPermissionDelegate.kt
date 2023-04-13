package dev.shushant.permissionframework.permissions.ios


import dev.shushant.permissionframework.permissions.Permission
import dev.shushant.permissionframework.permissions.PermissionState
import dev.shushant.permissionframework.permissions.mainContinuation
import dev.shushant.permissionframework.permissions.DeniedAlwaysException
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatus
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNAuthorizationStatusDenied
import platform.UserNotifications.UNAuthorizationStatusEphemeral
import platform.UserNotifications.UNAuthorizationStatusNotDetermined
import platform.UserNotifications.UNAuthorizationStatusProvisional
import platform.UserNotifications.UNNotificationSettings
import platform.UserNotifications.UNUserNotificationCenter
import kotlin.coroutines.suspendCoroutine

internal class RemoteNotificationPermissionDelegate : PermissionDelegate {
    override suspend fun providePermission() {
        val currentCenter: UNUserNotificationCenter = UNUserNotificationCenter
            .currentNotificationCenter()

        val status: UNAuthorizationStatus = suspendCoroutine { continuation ->
            currentCenter.getNotificationSettingsWithCompletionHandler(
                mainContinuation { settings: UNNotificationSettings? ->
                    continuation.resumeWith(
                        Result.success(
                            settings?.authorizationStatus ?: UNAuthorizationStatusNotDetermined
                        )
                    )
                }
            )
        }
        when (status) {
            UNAuthorizationStatusAuthorized -> return
            UNAuthorizationStatusNotDetermined -> {
                val isSuccess = suspendCoroutine<Boolean> { continuation ->
                    UNUserNotificationCenter.currentNotificationCenter()
                        .requestAuthorizationWithOptions(
                            UNAuthorizationOptionSound
                                .or(UNAuthorizationOptionAlert)
                                .or(UNAuthorizationOptionBadge),
                            mainContinuation { isOk, error ->
                                if (isOk && error == null) {
                                    continuation.resumeWith(Result.success(true))
                                } else {
                                    continuation.resumeWith(Result.success(false))
                                }
                            }
                        )
                }
                if (isSuccess) {
                    providePermission()
                } else {
                    error("notifications permission failed")
                }
            }

            UNAuthorizationStatusDenied -> throw DeniedAlwaysException(Permission.REMOTE_NOTIFICATION)
            else -> error("notifications permission status $status")
        }
    }

    override suspend fun getPermissionState(): PermissionState {
        val currentCenter = UNUserNotificationCenter.currentNotificationCenter()

        val status = suspendCoroutine<UNAuthorizationStatus> { continuation ->
            currentCenter.getNotificationSettingsWithCompletionHandler(
                mainContinuation { settings: UNNotificationSettings? ->
                    continuation.resumeWith(
                        Result.success(
                            settings?.authorizationStatus ?: UNAuthorizationStatusNotDetermined
                        )
                    )
                })
        }
        return when (status) {
            UNAuthorizationStatusAuthorized,
            UNAuthorizationStatusProvisional,
            UNAuthorizationStatusEphemeral -> PermissionState.Granted

            UNAuthorizationStatusNotDetermined -> PermissionState.NotDetermined
            UNAuthorizationStatusDenied -> PermissionState.DeniedAlways
            else -> error("unknown push authorization status $status")
        }
    }
}
