package dev.shushant.sun_and_storm_kmp.pref

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import dev.shushant.sun_and_storm_kmp.data.data.CurrentWeatherResponse
import dev.shushant.sun_and_storm_kmp.data.data.PlaceSearchResponseItem
import dev.shushant.sun_and_storm_kmp.permissions.data.LocationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSettingsApi::class)
class AppSettings(private val settings: ObservableSettings) {


    val locationData: Flow<LocationData?> =
        settings.getStringOrNullFlow(LOCATION_DATA).map {
            it?.let {
                getLocationData(it)
            }
        }.distinctUntilChangedBy {
            it?.coordinates
        }

    val weatherData: Flow<CurrentWeatherResponse?> =
        settings.getStringOrNullFlow(WEATHER_DATA).map {
            it?.let {
                getWeatherData(it)
            }
        }.distinctUntilChangedBy {
            it?.currentWeather
        }

    val getSearchedPlaces: Flow<PlaceSearchResponseItem?> =
        settings.getStringOrNullFlow(SEARCHED_DATA).map {
            it?.let {
                getSearchedPlaces(it)
            }
        }.distinctUntilChangedBy {
            it?.address
        }


    fun updateLocationData(data: LocationData) {
        settings.putString(LOCATION_DATA, Json.encodeToString(data))
    }

    fun updateWeatherData(data: CurrentWeatherResponse) {
        settings.putString(WEATHER_DATA, Json.encodeToString(data))
    }

    fun updateSearchedList(data: PlaceSearchResponseItem) {
        settings.putString(SEARCHED_DATA, Json.encodeToString(data))
    }

    fun deleteData(key: String) {
        settings.remove(key)
    }

    private fun getLocationData(settingsString: String) =
        Json.decodeFromString<LocationData>(settingsString)

    private fun getWeatherData(settingsString: String) =
        Json.decodeFromString<CurrentWeatherResponse>(settingsString)

    private fun getSearchedPlaces(settingsString: String) =
        Json.decodeFromString<PlaceSearchResponseItem>(settingsString)


    companion object {
        const val LOCATION_DATA = "location_data"
        const val WEATHER_DATA = "weather_data"
        const val SEARCHED_DATA = "searched_data"
    }
}
