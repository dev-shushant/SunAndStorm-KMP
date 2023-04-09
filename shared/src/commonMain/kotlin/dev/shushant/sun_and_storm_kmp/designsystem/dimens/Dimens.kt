package dev.shushant.sun_and_storm_kmp.designsystem.dimens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.shushant.sun_and_storm_kmp.CurrentPlatform
import dev.shushant.sun_and_storm_kmp.Platform
import dev.shushant.sun_and_storm_kmp.designsystem.style.LocalAppDimens


sealed interface Dimensions {
    object sw320 : Dimensions

    object sw480 : Dimensions

    object sw600 : Dimensions

    object sw720 : Dimensions

}


internal data class DeviceConfiguration(
    val screenWidthDp: Int = 0, val screenHeight: Int = 0
)

internal val LocalAppConfiguration = staticCompositionLocalOf {
    DeviceConfiguration()
}

internal inline val Int.dpBasedOnScreenSize
    @Composable
    get() = run {
        if (CurrentPlatform.current.value == Platform.DESKTOP) {
            val density = LocalDensity.current
            (this * density.density).dp
        } else this
    }

internal inline val Double.dpBasedOnScreenSize
    @Composable
    get() = run {
        if (CurrentPlatform.current.value == Platform.DESKTOP) {
            val density = LocalDensity.current
            (this * density.density).dp
        } else this
    }

internal inline val Float.dpBasedOnScreenSize
    @Composable
    get() = run {
        val density = LocalDensity.current
        (this * density.density).dp
    }


/**
 * Convert a dimension to Ldpi(low-density) screens
 */
fun Float.toLdpi() = this.times(0.75).dp

/**
 * Convert a dimension to Hdpi(high-density) screens
 */
fun Float.toHdpi() = this.times(1.5).dp

/**
 * Convert a dimension to Xhdpi(extra-high-density) screens
 */
fun Float.toXhdpi() = this.times(2.0).dp


internal val Int.getDimens
    @Composable get() =
        when (LocalAppDimens.current) {
            Dimensions.sw320 -> this.toFloat().toLdpi()
            Dimensions.sw480 -> this.dp
            Dimensions.sw600 -> this.toFloat().toHdpi()
            Dimensions.sw720 -> this.toFloat().toXhdpi()
        }

internal val Float.getDimens
    @Composable get() =
        when (LocalAppDimens.current) {
            Dimensions.sw320 -> this.toLdpi()
            Dimensions.sw480 -> this.dp
            Dimensions.sw600 -> this.toHdpi()
            Dimensions.sw720 -> this.toXhdpi()
        }