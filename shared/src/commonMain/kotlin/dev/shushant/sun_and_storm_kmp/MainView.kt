package dev.shushant.sun_and_storm_kmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.DeviceConfiguration
import dev.shushant.sun_and_storm_kmp.main.MainScreen
import dev.shushant.sun_and_storm_kmp.designsystem.style.LocalGradientColors
import dev.shushant.sun_and_storm_kmp.designsystem.style.SunAndStormAppTheme
import dev.shushant.sun_and_storm_kmp.designsystem.style.SunAndStormBackground
import dev.shushant.sun_and_storm_kmp.designsystem.style.SunAndStormGradientBackground

internal val darkmodeState = mutableStateOf(false)
internal val PlatformState = mutableStateOf(Platform.ANDROID)
internal val safeAreaState = mutableStateOf(PaddingValues())
internal val SafeArea = compositionLocalOf { safeAreaState }
internal val CurrentPlatform = compositionLocalOf { PlatformState }
internal val DarkMode = compositionLocalOf { darkmodeState }

@Composable
internal fun SunAndStormApp(
    showNavRail: Boolean,
    deviceConfiguration: DeviceConfiguration = DeviceConfiguration(),
    modifier: Modifier = Modifier
) {
    val darkMode = isSystemInDarkTheme()
    LaunchedEffect(key1 = Unit) {
        darkmodeState.value = darkMode
    }
    SunAndStormAppTheme(deviceConfiguration = deviceConfiguration) {
        SunAndStormBackground {
            SunAndStormGradientBackground(
                gradientColors =
                LocalGradientColors.current
            ) {
                MainScreen(showNavRail, modifier)
            }
        }
    }
}

enum class Platform {
    IOS,
    DESKTOP,
    ANDROID,
    WEB
}