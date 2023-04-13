package dev.shushant.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("is_day")
    val isDay: Int? = 0, // 0
    @SerialName("temperature")
    val temperature: Double? = 0.0, // 1.8
    @SerialName("time")
    val time: String? = "", // 2023-04-09T04:00
    @SerialName("weathercode")
    val weathercode: Int? = 0, // 45
    @SerialName("winddirection")
    val winddirection: Double? = 0.0, // 338.0
    @SerialName("windspeed")
    val windspeed: Double? = 0.0 // 3.9
)