package dev.shushant.sun_and_storm_kmp.data

import dev.shushant.sun_and_storm_kmp.data.data.CurrentWeatherResponse
import dev.shushant.sun_and_storm_kmp.location.LocationData


sealed interface DashBoardScreenState {
    object Loading : DashBoardScreenState
    data class Error(val message: String) : DashBoardScreenState
    data class Success(
        val weatherData: CurrentWeatherResponse,
        val locationData:LocationData
    ) : DashBoardScreenState
}
