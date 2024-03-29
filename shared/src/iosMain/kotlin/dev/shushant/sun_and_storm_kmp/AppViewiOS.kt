package dev.shushant.sun_and_storm_kmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.util.DebugLogger
import com.seiko.imageloader.util.LogPriority
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.DeviceConfiguration
import dev.shushant.sun_and_storm_kmp.permissions.compose.rememberPermissionsControllerFactory
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

@Composable
internal fun AppViewIOS(bounds: IntSize?) {
    val size = remember { mutableStateOf(IntSize.Zero) }
    val permissionsController = rememberPermissionsControllerFactory()

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
                ),
                permissionsController = permissionsController.createPermissionsController()
            )
        }
    }
}

internal fun openApplicationSettings() {
    UIApplication.sharedApplication().openURL(
        NSURL(string = UIApplicationOpenSettingsURLString)
    )
}
