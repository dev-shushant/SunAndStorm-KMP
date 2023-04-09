package dev.shushant.sun_and_storm_kmp.data.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    @SerialName("current_weather")
    val currentWeather: CurrentWeather? = CurrentWeather(),
    @SerialName("elevation")
    val elevation: Double? = 0.0, // 38.0
    @SerialName("generationtime_ms")
    val generationtimeMs: Double? = 0.0, // 0.11098384857177734
    @SerialName("latitude")
    val latitude: Double? = 0.0, // 52.52
    @SerialName("longitude")
    val longitude: Double? = 0.0, // 13.419998
    @SerialName("timezone")
    val timezone: String? = "", // GMT
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String? = "", // GMT
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int? = 0 // 0
)