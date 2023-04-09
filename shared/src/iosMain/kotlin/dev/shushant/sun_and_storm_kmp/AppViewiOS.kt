package dev.shushant.sun_and_storm_kmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import co.touchlab.kermit.Logger
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.DeviceConfiguration
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

@Composable
internal fun AppViewiOS(bounds: IntSize?) {
    val size = remember { mutableStateOf(IntSize.Zero) }
    var isOpened by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.Default) {
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
                        openApplicationSettings()
                    }
                    Logger.d { "onLocationUnavailable" }
                    print("onLocationUnavailable")
                }
                .onLocationUpdated("SingleRequest") { data ->
                    print("Single" + data.coordinates.toString())
                    print("Single: " + data.coordinates.toString())
                }.startLocationUpdating()
        }
    }
    Box(Modifier.fillMaxSize().onGloballyPositioned { coordinates ->
        size.value = coordinates.size
    }) {
        CompositionLocalProvider(
            LocalImageLoader provides ImageLoader {
                logger = DebugLogger(LogPriority.VERBOSE)
                components {
                    setupDefaultComponents(imageScope)
                }
                interceptor {
                    memoryCacheConfig {
                        maxSizePercent(0.25)
                    }
                }
            },
        ) {
            SunAndStormApp(
                false, deviceConfiguration = DeviceConfiguration(
                    screenWidthDp = bounds?.width ?: 0,
                    screenHeight = bounds?.height ?: 0
                )
            )
        }
    }
}

internal fun openApplicationSettings() {
    UIApplication.sharedApplication().openURL(
        NSURL(string = UIApplicationOpenSettingsURLString)
    )
}
