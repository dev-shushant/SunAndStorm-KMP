package dev.shushant.sun_and_storm_kmp.ui.dashboard

import dev.shushant.sun_and_storm_kmp.data.data.CurrentWeatherResponse
import dev.shushant.sun_and_storm_kmp.permissions.data.LocationData


sealed interface DashBoardScreenState {
    object Loading : DashBoardScreenState
    data class Error(val message: String) : DashBoardScreenState
    data class Success(
        val weatherData: CurrentWeatherResponse,
        val locationData: LocationData,
    ) : DashBoardScreenState
}
