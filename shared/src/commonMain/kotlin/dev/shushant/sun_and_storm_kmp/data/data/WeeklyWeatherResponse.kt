package dev.shushant.sun_and_storm_kmp.data.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeeklyWeatherResponse(
    @SerialName("current_weather")
    val currentWeather: CurrentWeather? = CurrentWeather(),
    @SerialName("daily")
    val daily: Daily? = Daily(),
    @SerialName("daily_units")
    val dailyUnits: DailyUnits? = DailyUnits(),
    @SerialName("elevation")
    val elevation: Double? = 0.0, // 232.0
    @SerialName("generationtime_ms")
    val generationtimeMs: Double? = 0.0, // 38.043975830078125
    @SerialName("latitude")
    val latitude: Double? = 0.0, // 28.5
    @SerialName("longitude")
    val longitude: Double? = 0.0, // 77.125
    @SerialName("timezone")
    val timezone: String? = "", // Asia/Kolkata
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String? = "", // IST
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int? = 0 // 19800
) {
    @Serializable
    data class CurrentWeather(
        @SerialName("is_day")
        val isDay: Int? = 0, // 0
        @SerialName("temperature")
        val temperature: Double? = 0.0, // 29.6
        @SerialName("time")
        val time: String? = "", // 2023-04-14T21:30
        @SerialName("weathercode")
        val weathercode: Int? = 0, // 1
        @SerialName("winddirection")
        val winddirection: Double? = 0.0, // 174.0
        @SerialName("windspeed")
        val windspeed: Double? = 0.0 // 3.6
    )

    @Serializable
    data class Daily(
        @SerialName("apparent_temperature_max")
        val apparentTemperatureMax: List<Double?>? = listOf(),
        @SerialName("apparent_temperature_min")
        val apparentTemperatureMin: List<Double?>? = listOf(),
        @SerialName("precipitation_hours")
        val precipitationHours: List<Double?>? = listOf(),
        @SerialName("rain_sum")
        val rainSum: List<Double?>? = listOf(),
        @SerialName("showers_sum")
        val showersSum: List<Double?>? = listOf(),
        @SerialName("snowfall_sum")
        val snowfallSum: List<Double?>? = listOf(),
        @SerialName("sunrise")
        val sunrise: List<String?>? = listOf(),
        @SerialName("sunset")
        val sunset: List<String?>? = listOf(),
        @SerialName("temperature_2m_max")
        val temperature2mMax: List<Double?>? = listOf(),
        @SerialName("temperature_2m_min")
        val temperature2mMin: List<Double?>? = listOf(),
        @SerialName("time")
        val time: List<String?>? = listOf(),
        @SerialName("weathercode")
        val weathercode: List<Int?>? = listOf()
    )

    @Serializable
    data class DailyUnits(
        @SerialName("apparent_temperature_max")
        val apparentTemperatureMax: String? = "", // °C
        @SerialName("apparent_temperature_min")
        val apparentTemperatureMin: String? = "", // °C
        @SerialName("precipitation_hours")
        val precipitationHours: String? = "", // h
        @SerialName("rain_sum")
        val rainSum: String? = "", // mm
        @SerialName("showers_sum")
        val showersSum: String? = "", // mm
        @SerialName("snowfall_sum")
        val snowfallSum: String? = "", // cm
        @SerialName("sunrise")
        val sunrise: String? = "", // iso8601
        @SerialName("sunset")
        val sunset: String? = "", // iso8601
        @SerialName("temperature_2m_max")
        val temperature2mMax: String? = "", // °C
        @SerialName("temperature_2m_min")
        val temperature2mMin: String? = "", // °C
        @SerialName("time")
        val time: String? = "", // iso8601
        @SerialName("weathercode")
        val weathercode: String? = "" // wmo code
    )
}