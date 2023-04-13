package dev.shushant.sun_and_storm_kmp.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import co.touchlab.kermit.Logger
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.shushant.sun_and_storm_kmp.Platform
import dev.shushant.sun_and_storm_kmp.PlatformState
import dev.shushant.sun_and_storm_kmp.SunAndStormApp
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.DeviceConfiguration
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController
import dev.shushant.sun_and_storm_kmp.util.BackPressHandler

@Composable
fun AppViewAndroid(permissionController: PermissionsController) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val configuration = LocalConfiguration.current
    PlatformState.value = Platform.ANDROID

    BackPressHandler {
        Logger.e { "BackPressHandler" }
    }
    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        onDispose {}
    }
    SunAndStormApp(
        false,
        deviceConfiguration = DeviceConfiguration(
            configuration.screenWidthDp,
            configuration.screenHeightDp
        ),
        permissionsController = permissionController
    )
}