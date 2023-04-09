package dev.shushant.sun_and_storm_kmp.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import co.touchlab.kermit.Logger
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.shushant.sun_and_storm_kmp.Platform
import dev.shushant.sun_and_storm_kmp.PlatformState
import dev.shushant.sun_and_storm_kmp.SunAndStormApp
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.DeviceConfiguration
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AppViewAndroid() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var isOpened by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    PlatformState.value = Platform.ANDROID
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

        coroutineScope.launch(Dispatchers.Default){
            SunAndStormLocation
                .onPermissionUpdated("SingleRequest") { isGranted ->
                    if (!isGranted) {
                        isOpened = true
                        print("onPermissionUpdated NotGranted")
                        Logger.d { "onPermissionUpdated NotGranted" }
                    } else {
                        isOpened = false
                    }
                }
                .onLocationUnavailable("SingleRequest") {
                    if (isOpened) {
                        isOpened = false
                        context.openApplicationSettings()
                    }
                    Logger.d { "onLocationUnavailable" }
                    print("onLocationUnavailable")
                }
                .onLocationUpdated("SingleRequest") { data ->
                    print("Single" + data.coordinates.toString())
                    print("Single: " + data.coordinates.toString())
                }.startLocationUpdating()
        }

        onDispose {}
    }
    SunAndStormApp(false, deviceConfiguration = DeviceConfiguration(configuration.screenWidthDp,configuration.screenHeightDp))
}

fun Context.openApplicationSettings() {
    startActivity(Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.parse("package:${packageName}")
    })
}
