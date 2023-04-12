package dev.shushant.sun_and_storm_kmp.permissions.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.shushant.sun_and_storm_kmp.permissions.ios.PermissionsController

@Composable
internal actual fun rememberPermissionsControllerFactory(): PermissionsControllerFactory {
    return remember {
        PermissionsControllerFactory {
            PermissionsController()
        }
    }
}
