package dev.shushant.sun_and_storm_kmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import dev.shushant.sun_and_storm_kmp.details.DetailScreen
import dev.shushant.sun_and_storm_kmp.listing.ListScreenViewModel
import dev.shushant.sun_and_storm_kmp.listing.MainScreen
import dev.shushant.sun_and_storm_kmp.screennavigation.Screen
import dev.shushant.sun_and_storm_kmp.screennavigation.ScreensState
import dev.shushant.sun_and_storm_kmp.style.TravelAppColors
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun CommonView(width: Float = 200f) {
    val viewMode = ListScreenViewModel()
    val screenNavigationState = remember { mutableStateOf(ScreensState()) }

    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = TravelAppColors.Foreground,
            secondary = TravelAppColors.LightGray,
            background = TravelAppColors.DarkGray,
            surface = TravelAppColors.Gray,
            onPrimary = TravelAppColors.Foreground,
            onSecondary = Color.Black,
            onBackground = TravelAppColors.Foreground,
            onSurface = TravelAppColors.Foreground
        )
    ) {
        Box {
            Image(
                painter = painterResource("background.png"),
                "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            when (val state = screenNavigationState.value.screen) {
                is Screen.DetailScreen -> DetailScreen(
                    navigationState = screenNavigationState,
                    touristPlace = state.touristPlace
                )

                Screen.MainScreen -> MainScreen(screenNavigationState, viewMode, width)
            }
        }

    }
}