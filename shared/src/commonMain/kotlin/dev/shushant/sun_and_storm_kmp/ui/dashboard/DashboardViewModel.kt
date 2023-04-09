package dev.shushant.sun_and_storm_kmp.ui.dashboard

import dev.shushant.sun_and_storm_kmp.CurrentPlatform
import dev.shushant.sun_and_storm_kmp.Platform
import dev.shushant.sun_and_storm_kmp.data.DashBoardScreenState
import dev.shushant.sun_and_storm_kmp.location.Coordinates
import dev.shushant.sun_and_storm_kmp.location.LocationData
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation
import dev.shushant.sun_and_storm_kmp.network.WeatherApi
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DashboardViewModel : ViewModel(), KoinComponent {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val weatherApi: WeatherApi by inject()
    private val appSettings: AppSettings by inject()

    private val locationData = appSettings.locationData

    val state = MutableStateFlow<DashBoardScreenState>(DashBoardScreenState.Loading)

    init {
        viewModelScope.launch {
            collectLocationData()
        }
    }

    private suspend fun collectWeatherData(locationData: LocationData?) {
        appSettings.weatherData.collectLatest { data ->
            data?.let {
                state.emit(
                    DashBoardScreenState.Success(
                        locationData = locationData ?: LocationData(),
                        weatherData = data
                    )
                )
            }
            if (validateLatLng(
                    locationData?.coordinates?.latitude ?: 0.0,
                    locationData?.coordinates?.longitude ?: 0.0
                )
            ) {
                locationData?.coordinates?.let { getWeather(it) }
            } else {
                getWeather(Coordinates())
            }
        }
    }

    internal var platform: Platform? = null

    private suspend fun collectLocationData() {
        locationData.collectLatest {
            val shouldRefresh = GMTDate(it?.dateTime?.toLong()).hours + 0.1 < GMTDate().hours
            while (it == null || shouldRefresh) {
                currentLocation()
                delay(3000L)
                if (platform == Platform.DESKTOP) {
                    break
                }
            }
            collectWeatherData(it)
        }
    }

    private suspend fun currentLocation() {
        kotlin.runCatching {
            SunAndStormLocation.currentLocation {
                appSettings.updateLocationData(it)
            }
        }.getOrElse {
            getWeather(Coordinates())
        }
    }

    private suspend fun getWeather(locationData: Coordinates) {
        appSettings.weatherData.first()?.let {
            appSettings.updateWeatherData(it)
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            kotlin.runCatching {
                val response = weatherApi.getWeatherInfo(locationData)
                appSettings.updateWeatherData(response)
            }.getOrElse {
                state.emit(DashBoardScreenState.Error(it.cause?.message ?: "_ _"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    private fun validateLatLng(latitude: Double, longitude: Double): Boolean {
        val latRange = -90.0..90.0
        val longRange = -180.0..180.0

        // Check if latitude and longitude values are within valid ranges
        if (latitude !in latRange || longitude !in longRange) {
            return false
        }

        // Check if latitude and longitude are not both zero
        if (latitude == 0.0 && longitude == 0.0) {
            return false
        }

        // If all checks pass, return true
        return true
    }
}