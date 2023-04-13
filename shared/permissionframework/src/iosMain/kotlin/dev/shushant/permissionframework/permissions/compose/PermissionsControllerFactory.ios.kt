package dev.shushant.permissionframework.permissions.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.shushant.permissionframework.permissions.ios.PermissionsController

@Composable
internal actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    return remember {
        PermissionsControllerFactory {
            PermissionsController()
        }
    }
}
