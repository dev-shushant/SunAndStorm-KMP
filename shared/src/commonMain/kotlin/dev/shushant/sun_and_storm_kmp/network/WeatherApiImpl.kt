package dev.shushant.sun_and_storm_kmp.network

import co.touchlab.kermit.Logger
import dev.shushant.sun_and_storm_kmp.data.data.CurrentWeatherResponse
import dev.shushant.sun_and_storm_kmp.data.data.PlaceSearchResponseItem
import dev.shushant.sun_and_storm_kmp.data.data.WeeklyWeatherResponse
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates
import dev.shushant.sun_and_storm_kmp.util.getDate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlin.time.ExperimentalTime

class WeatherApiImpl(private val client: HttpClient) : WeatherApi {
    override suspend fun getWeatherInfo(coordinate: Coordinates): CurrentWeatherResponse {
        return client.get {
            getWeatherDetails(coordinate = coordinate)
        }.body<CurrentWeatherResponse>().also { Logger.v { it.toString() } }
    }

    override suspend fun getPlacesBySearch(place: String): List<PlaceSearchResponseItem> {
        return client.get {
            getPlaces(place)
        }.body()
    }

    override suspend fun getWeeklyWeatherDetails(coordinate: Coordinates): WeeklyWeatherResponse {
        return client.get {
            getWeeklyWeatherDetails(coordinate)
        }.body()
    }

    private fun HttpRequestBuilder.getWeatherDetails(coordinate: Coordinates) {
        url {
            takeFrom("https://api.open-meteo.com/v1/forecast?latitude=${coordinate.latitude}&longitude=${coordinate.longitude}&current_weather=true")
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun HttpRequestBuilder.getWeeklyWeatherDetails(coordinate: Coordinates) {
        val pair = getDate()
        url {
            takeFrom(
                "https://api.open-meteo.com/v1/forecast?latitude=${coordinate.latitude}&longitude=${coordinate.longitude}6&daily=temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,rain_sum,showers_sum,snowfall_sum,weathercode,sunrise,precipitation_hours,sunset&current_weather=true&start_date=${pair.first}&end_date=${pair.second}&timezone=auto"
            )
        }
    }

    private fun HttpRequestBuilder.getPlaces(places: String) {
        url {
            takeFrom("https://nominatim.openstreetmap.org/search/${places}?format=json")
        }
    }
}