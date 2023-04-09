package dev.shushant.sun_and_storm_kmp.designsystem.style

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A class to model background color and tonal elevation values for Now in Android.
 */
@Immutable
internal data class TintTheme(
    val iconTint: Color? = null,
)

/**
 * A composition local for [TintTheme].
 */
internal val LocalTintTheme = staticCompositionLocalOf { TintTheme() }
