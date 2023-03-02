package dev.shushant.sun_and_storm_kmp.screennavigation

import dev.shushant.sun_and_storm_kmp.listing.TouristPlace

sealed interface Screen {
    object MainScreen : Screen
    data class DetailScreen(val touristPlace: TouristPlace) : Screen
}

data class ScreensState(val screen: Screen = Screen.MainScreen)