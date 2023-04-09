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
    val elevation: Double? = 0.0, // 230.0
    @SerialName("generationtime_ms")
    val generationtimeMs: Double? = 0.0, // 10.55908203125
    @SerialName("hourly")
    val hourly: Hourly? = Hourly(),
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits? = HourlyUnits(),
    @SerialName("latitude")
    val latitude: Double? = 0.0, // 28.5
    @SerialName("longitude")
    val longitude: Double? = 0.0, // 77.0
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
        val isDay: Int? = 0, // 1
        @SerialName("temperature")
        val temperature: Double? = 0.0, // 34.4
        @SerialName("time")
        val time: String? = "", // 2023-04-09T15:30
        @SerialName("weathercode")
        val weathercode: Int? = 0, // 0
        @SerialName("winddirection")
        val winddirection: Double? = 0.0, // 286.0
        @SerialName("windspeed")
        val windspeed: Double? = 0.0 // 11.6
    )

    @Serializable
    data class Daily(
        @SerialName("apparent_temperature_max")
        val apparentTemperatureMax: List<Double?>? = listOf(),
        @SerialName("apparent_temperature_min")
        val apparentTemperatureMin: List<Double?>? = listOf(),
        @SerialName("et0_fao_evapotranspiration")
        val et0FaoEvapotranspiration: List<Double?>? = listOf(),
        @SerialName("precipitation_hours")
        val precipitationHours: List<Double?>? = listOf(),
        @SerialName("precipitation_probability_max")
        val precipitationProbabilityMax: List<Int?>? = listOf(),
        @SerialName("precipitation_sum")
        val precipitationSum: List<Double?>? = listOf(),
        @SerialName("rain_sum")
        val rainSum: List<Double?>? = listOf(),
        @SerialName("shortwave_radiation_sum")
        val shortwaveRadiationSum: List<Double?>? = listOf(),
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
        @SerialName("uv_index_clear_sky_max")
        val uvIndexClearSkyMax: List<Double?>? = listOf(),
        @SerialName("uv_index_max")
        val uvIndexMax: List<Double?>? = listOf(),
        @SerialName("weathercode")
        val weathercode: List<Int?>? = listOf(),
        @SerialName("winddirection_10m_dominant")
        val winddirection10mDominant: List<Int?>? = listOf(),
        @SerialName("windgusts_10m_max")
        val windgusts10mMax: List<Double?>? = listOf(),
        @SerialName("windspeed_10m_max")
        val windspeed10mMax: List<Double?>? = listOf()
    )

    @Serializable
    data class DailyUnits(
        @SerialName("apparent_temperature_max")
        val apparentTemperatureMax: String? = "", // °C
        @SerialName("apparent_temperature_min")
        val apparentTemperatureMin: String? = "", // °C
        @SerialName("et0_fao_evapotranspiration")
        val et0FaoEvapotranspiration: String? = "", // mm
        @SerialName("precipitation_hours")
        val precipitationHours: String? = "", // h
        @SerialName("precipitation_probability_max")
        val precipitationProbabilityMax: String? = "", // %
        @SerialName("precipitation_sum")
        val precipitationSum: String? = "", // mm
        @SerialName("rain_sum")
        val rainSum: String? = "", // mm
        @SerialName("shortwave_radiation_sum")
        val shortwaveRadiationSum: String? = "", // MJ/m²
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
        @SerialName("uv_index_clear_sky_max")
        val uvIndexClearSkyMax: String? = "",
        @SerialName("uv_index_max")
        val uvIndexMax: String? = "",
        @SerialName("weathercode")
        val weathercode: String? = "", // wmo code
        @SerialName("winddirection_10m_dominant")
        val winddirection10mDominant: String? = "", // °
        @SerialName("windgusts_10m_max")
        val windgusts10mMax: String? = "", // km/h
        @SerialName("windspeed_10m_max")
        val windspeed10mMax: String? = "" // km/h
    )

    @Serializable
    data class Hourly(
        @SerialName("apparent_temperature")
        val apparentTemperature: List<Double?>? = listOf(),
        @SerialName("cape")
        val cape: List<Double?>? = listOf(),
        @SerialName("cloudcover")
        val cloudcover: List<Int?>? = listOf(),
        @SerialName("cloudcover_high")
        val cloudcoverHigh: List<Int?>? = listOf(),
        @SerialName("cloudcover_low")
        val cloudcoverLow: List<Int?>? = listOf(),
        @SerialName("cloudcover_mid")
        val cloudcoverMid: List<Int?>? = listOf(),
        @SerialName("dewpoint_2m")
        val dewpoint2m: List<Double?>? = listOf(),
        @SerialName("diffuse_radiation")
        val diffuseRadiation: List<Double?>? = listOf(),
        @SerialName("diffuse_radiation_instant")
        val diffuseRadiationInstant: List<Double?>? = listOf(),
        @SerialName("direct_normal_irradiance")
        val directNormalIrradiance: List<Double?>? = listOf(),
        @SerialName("direct_normal_irradiance_instant")
        val directNormalIrradianceInstant: List<Double?>? = listOf(),
        @SerialName("direct_radiation")
        val directRadiation: List<Double?>? = listOf(),
        @SerialName("direct_radiation_instant")
        val directRadiationInstant: List<Double?>? = listOf(),
        @SerialName("et0_fao_evapotranspiration")
        val et0FaoEvapotranspiration: List<Double?>? = listOf(),
        @SerialName("evapotranspiration")
        val evapotranspiration: List<Double?>? = listOf(),
        @SerialName("freezinglevel_height")
        val freezinglevelHeight: List<Double?>? = listOf(),
        @SerialName("is_day")
        val isDay: List<Int?>? = listOf(),
        @SerialName("precipitation")
        val precipitation: List<Double?>? = listOf(),
        @SerialName("precipitation_probability")
        val precipitationProbability: List<Int?>? = listOf(),
        @SerialName("pressure_msl")
        val pressureMsl: List<Double?>? = listOf(),
        @SerialName("rain")
        val rain: List<Double?>? = listOf(),
        @SerialName("relativehumidity_2m")
        val relativehumidity2m: List<Int?>? = listOf(),
        @SerialName("shortwave_radiation")
        val shortwaveRadiation: List<Double?>? = listOf(),
        @SerialName("shortwave_radiation_instant")
        val shortwaveRadiationInstant: List<Double?>? = listOf(),
        @SerialName("showers")
        val showers: List<Double?>? = listOf(),
        @SerialName("snow_depth")
        val snowDepth: List<Double?>? = listOf(),
        @SerialName("snowfall")
        val snowfall: List<Double?>? = listOf(),
        @SerialName("soil_moisture_0_1cm")
        val soilMoisture01cm: List<Double?>? = listOf(),
        @SerialName("soil_moisture_1_3cm")
        val soilMoisture13cm: List<Double?>? = listOf(),
        @SerialName("soil_moisture_27_81cm")
        val soilMoisture2781cm: List<Double?>? = listOf(),
        @SerialName("soil_moisture_3_9cm")
        val soilMoisture39cm: List<Double?>? = listOf(),
        @SerialName("soil_moisture_9_27cm")
        val soilMoisture927cm: List<Double?>? = listOf(),
        @SerialName("soil_temperature_0cm")
        val soilTemperature0cm: List<Double?>? = listOf(),
        @SerialName("soil_temperature_18cm")
        val soilTemperature18cm: List<Double?>? = listOf(),
        @SerialName("soil_temperature_54cm")
        val soilTemperature54cm: List<Double?>? = listOf(),
        @SerialName("soil_temperature_6cm")
        val soilTemperature6cm: List<Double?>? = listOf(),
        @SerialName("surface_pressure")
        val surfacePressure: List<Double?>? = listOf(),
        @SerialName("temperature_120m")
        val temperature120m: List<Double?>? = listOf(),
        @SerialName("temperature_180m")
        val temperature180m: List<Double?>? = listOf(),
        @SerialName("temperature_2m")
        val temperature2m: List<Double?>? = listOf(),
        @SerialName("temperature_80m")
        val temperature80m: List<Double?>? = listOf(),
        @SerialName("terrestrial_radiation")
        val terrestrialRadiation: List<Double?>? = listOf(),
        @SerialName("terrestrial_radiation_instant")
        val terrestrialRadiationInstant: List<Double?>? = listOf(),
        @SerialName("time")
        val time: List<String?>? = listOf(),
        @SerialName("uv_index")
        val uvIndex: List<Double?>? = listOf(),
        @SerialName("uv_index_clear_sky")
        val uvIndexClearSky: List<Double?>? = listOf(),
        @SerialName("vapor_pressure_deficit")
        val vaporPressureDeficit: List<Double?>? = listOf(),
        @SerialName("visibility")
        val visibility: List<Double?>? = listOf(),
        @SerialName("weathercode")
        val weathercode: List<Int?>? = listOf(),
        @SerialName("winddirection_10m")
        val winddirection10m: List<Int?>? = listOf(),
        @SerialName("winddirection_120m")
        val winddirection120m: List<Int?>? = listOf(),
        @SerialName("winddirection_180m")
        val winddirection180m: List<Int?>? = listOf(),
        @SerialName("winddirection_80m")
        val winddirection80m: List<Int?>? = listOf(),
        @SerialName("windgusts_10m")
        val windgusts10m: List<Double?>? = listOf(),
        @SerialName("windspeed_10m")
        val windspeed10m: List<Double?>? = listOf(),
        @SerialName("windspeed_120m")
        val windspeed120m: List<Double?>? = listOf(),
        @SerialName("windspeed_180m")
        val windspeed180m: List<Double?>? = listOf(),
        @SerialName("windspeed_80m")
        val windspeed80m: List<Double?>? = listOf()
    )

    @Serializable
    data class HourlyUnits(
        @SerialName("apparent_temperature")
        val apparentTemperature: String? = "", // °C
        @SerialName("cape")
        val cape: String? = "", // J/kg
        @SerialName("cloudcover")
        val cloudcover: String? = "", // %
        @SerialName("cloudcover_high")
        val cloudcoverHigh: String? = "", // %
        @SerialName("cloudcover_low")
        val cloudcoverLow: String? = "", // %
        @SerialName("cloudcover_mid")
        val cloudcoverMid: String? = "", // %
        @SerialName("dewpoint_2m")
        val dewpoint2m: String? = "", // °C
        @SerialName("diffuse_radiation")
        val diffuseRadiation: String? = "", // W/m²
        @SerialName("diffuse_radiation_instant")
        val diffuseRadiationInstant: String? = "", // W/m²
        @SerialName("direct_normal_irradiance")
        val directNormalIrradiance: String? = "", // W/m²
        @SerialName("direct_normal_irradiance_instant")
        val directNormalIrradianceInstant: String? = "", // W/m²
        @SerialName("direct_radiation")
        val directRadiation: String? = "", // W/m²
        @SerialName("direct_radiation_instant")
        val directRadiationInstant: String? = "", // W/m²
        @SerialName("et0_fao_evapotranspiration")
        val et0FaoEvapotranspiration: String? = "", // mm
        @SerialName("evapotranspiration")
        val evapotranspiration: String? = "", // mm
        @SerialName("freezinglevel_height")
        val freezinglevelHeight: String? = "", // m
        @SerialName("is_day")
        val isDay: String? = "",
        @SerialName("precipitation")
        val precipitation: String? = "", // mm
        @SerialName("precipitation_probability")
        val precipitationProbability: String? = "", // %
        @SerialName("pressure_msl")
        val pressureMsl: String? = "", // hPa
        @SerialName("rain")
        val rain: String? = "", // mm
        @SerialName("relativehumidity_2m")
        val relativehumidity2m: String? = "", // %
        @SerialName("shortwave_radiation")
        val shortwaveRadiation: String? = "", // W/m²
        @SerialName("shortwave_radiation_instant")
        val shortwaveRadiationInstant: String? = "", // W/m²
        @SerialName("showers")
        val showers: String? = "", // mm
        @SerialName("snow_depth")
        val snowDepth: String? = "", // m
        @SerialName("snowfall")
        val snowfall: String? = "", // cm
        @SerialName("soil_moisture_0_1cm")
        val soilMoisture01cm: String? = "", // m³/m³
        @SerialName("soil_moisture_1_3cm")
        val soilMoisture13cm: String? = "", // m³/m³
        @SerialName("soil_moisture_27_81cm")
        val soilMoisture2781cm: String? = "", // m³/m³
        @SerialName("soil_moisture_3_9cm")
        val soilMoisture39cm: String? = "", // m³/m³
        @SerialName("soil_moisture_9_27cm")
        val soilMoisture927cm: String? = "", // m³/m³
        @SerialName("soil_temperature_0cm")
        val soilTemperature0cm: String? = "", // °C
        @SerialName("soil_temperature_18cm")
        val soilTemperature18cm: String? = "", // °C
        @SerialName("soil_temperature_54cm")
        val soilTemperature54cm: String? = "", // °C
        @SerialName("soil_temperature_6cm")
        val soilTemperature6cm: String? = "", // °C
        @SerialName("surface_pressure")
        val surfacePressure: String? = "", // hPa
        @SerialName("temperature_120m")
        val temperature120m: String? = "", // °C
        @SerialName("temperature_180m")
        val temperature180m: String? = "", // °C
        @SerialName("temperature_2m")
        val temperature2m: String? = "", // °C
        @SerialName("temperature_80m")
        val temperature80m: String? = "", // °C
        @SerialName("terrestrial_radiation")
        val terrestrialRadiation: String? = "", // W/m²
        @SerialName("terrestrial_radiation_instant")
        val terrestrialRadiationInstant: String? = "", // W/m²
        @SerialName("time")
        val time: String? = "", // iso8601
        @SerialName("uv_index")
        val uvIndex: String? = "",
        @SerialName("uv_index_clear_sky")
        val uvIndexClearSky: String? = "",
        @SerialName("vapor_pressure_deficit")
        val vaporPressureDeficit: String? = "", // kPa
        @SerialName("visibility")
        val visibility: String? = "", // m
        @SerialName("weathercode")
        val weathercode: String? = "", // wmo code
        @SerialName("winddirection_10m")
        val winddirection10m: String? = "", // °
        @SerialName("winddirection_120m")
        val winddirection120m: String? = "", // °
        @SerialName("winddirection_180m")
        val winddirection180m: String? = "", // °
        @SerialName("winddirection_80m")
        val winddirection80m: String? = "", // °
        @SerialName("windgusts_10m")
        val windgusts10m: String? = "", // km/h
        @SerialName("windspeed_10m")
        val windspeed10m: String? = "", // km/h
        @SerialName("windspeed_120m")
        val windspeed120m: String? = "", // km/h
        @SerialName("windspeed_180m")
        val windspeed180m: String? = "", // km/h
        @SerialName("windspeed_80m")
        val windspeed80m: String? = "" // km/h
    )
}