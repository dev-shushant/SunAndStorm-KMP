package dev.shushant.sun_and_storm_kmp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApplication
import org.jetbrains.skiko.SystemTheme
import org.jetbrains.skiko.currentSystemTheme
import platform.CoreGraphics.CGFloat

internal var boundss: IntSize? = null
fun MainViewController() = PreComposeApplication("SunAndStorm-KMP") {
    AppViewiOS(boundss)
}

fun setSafeArea(
    start: CGFloat,
    top: CGFloat,
    end: CGFloat,
    bottom: CGFloat,
    width: CGFloat,
    height: CGFloat
) {
    boundss = IntSize(width.toInt(), height.toInt())
    safeAreaState.value = PaddingValues(start.dp, top.dp, end.dp, bottom.dp)
    PlatformState.value = Platform.IOS
}

fun setDarkMode() {
    darkmodeState.value = currentSystemTheme == SystemTheme.DARK
}
