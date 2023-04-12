package dev.shushant.sun_and_storm_kmp.network

import co.touchlab.kermit.Logger
import dev.shushant.sun_and_storm_kmp.data.data.CurrentWeatherResponse
import dev.shushant.sun_and_storm_kmp.data.data.PlaceSearchResponseItem
import dev.shushant.sun_and_storm_kmp.data.data.WeeklyWeatherResponse
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import io.ktor.util.date.GMTDate
import io.ktor.util.date.plus
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

class WeatherApiImpl(private val client: HttpClient) :
    WeatherApi {
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
            getWeatherDetails(coordinate)
        }.body()
    }

    private fun HttpRequestBuilder.getWeatherDetails(coordinate: Coordinates) {
        url {
            takeFrom("https://api.open-meteo.com/v1/forecast?latitude=${coordinate.latitude}&longitude=${coordinate.longitude}&current_weather=true")
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun HttpRequestBuilder.getWeeklyWeatherDetails(coordinate: Coordinates) {
        val startDate = GMTDate().let {
            "${it.year}-${it.month.ordinal}-${it.dayOfMonth}"
        }
        val lastDate = GMTDate().plus(7.days).let {
            "${it.year}-${it.month.ordinal}-${it.dayOfMonth}"
        }
        url {
            takeFrom(
                "https://api.open-meteo.com/v1/forecast?latitude=${coordinate.latitude}&longitude=${coordinate.longitude}6&hourly=temperature_2m,relativehumidity_2m,dewpoint_2m,apparent_temperature,precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weathercode,pressure_msl,surface_pressure,cloudcover,cloudcover_low,cloudcover_mid,cloudcover_high,visibility,evapotranspiration,et0_fao_evapotranspiration,vapor_pressure_deficit,windspeed_10m,windspeed_80m,windspeed_120m,windspeed_180m,winddirection_10m,winddirection_80m,winddirection_120m,winddirection_180m,windgusts_10m,temperature_80m,temperature_120m,temperature_180m,soil_temperature_0cm,soil_temperature_6cm,soil_temperature_18cm,soil_temperature_54cm,soil_moisture_0_1cm,soil_moisture_1_3cm,soil_moisture_3_9cm,soil_moisture_9_27cm,soil_moisture_27_81cm,uv_index,uv_index_clear_sky,is_day,cape,freezinglevel_height,shortwave_radiation,direct_radiation,diffuse_radiation,direct_normal_irradiance,terrestrial_radiation,shortwave_radiation_instant,direct_radiation_instant,diffuse_radiation_instant,direct_normal_irradiance_instant,terrestrial_radiation_instant&models=best_match&daily=weathercode,temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset,uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,windspeed_10m_max,windgusts_10m_max,winddirection_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration&current_weather=true&start_date=$startDate&end_date=$lastDate&timezone=auto"
            )
        }
    }

    private fun HttpRequestBuilder.getPlaces(places: String) {
        url {
            takeFrom("https://nominatim.openstreetmap.org/search/${places}?format=json")
        }
    }
}