package dev.shushant.sun_and_storm_kmp.network

import dev.shushant.sun_and_storm_kmp.data.data.CurrentWeatherResponse
import dev.shushant.sun_and_storm_kmp.data.data.PlaceSearchResponseItem
import dev.shushant.sun_and_storm_kmp.data.data.WeeklyWeatherResponse
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates

interface WeatherApi {
    suspend fun getWeatherInfo(coordinate: Coordinates): CurrentWeatherResponse
    suspend fun getPlacesBySearch(place: String): List<PlaceSearchResponseItem>
    suspend fun getWeeklyWeatherDetails(coordinate: Coordinates): WeeklyWeatherResponse
}