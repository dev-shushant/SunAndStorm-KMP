package dev.shushant.sun_and_storm_kmp.ui.dashboard

import co.touchlab.kermit.Logger
import dev.shushant.sun_and_storm_kmp.data.data.WeeklyWeatherResponse
import dev.shushant.sun_and_storm_kmp.network.WeatherApi
import dev.shushant.sun_and_storm_kmp.permissions.DeniedAlwaysException
import dev.shushant.sun_and_storm_kmp.permissions.DeniedException
import dev.shushant.sun_and_storm_kmp.permissions.Permission
import dev.shushant.sun_and_storm_kmp.permissions.PermissionState
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController
import dev.shushant.sun_and_storm_kmp.permissions.data.Coordinates
import dev.shushant.sun_and_storm_kmp.permissions.data.LocationData
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DashboardViewModel(private val permissionsController: PermissionsController) :
    ViewModel(),
    KoinComponent {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val weatherApi: WeatherApi by inject()
    private val appSettings: AppSettings by inject()

    private val locationData = appSettings.locationData

    val state = MutableStateFlow<DashBoardScreenState>(DashBoardScreenState.Loading)
    val weeklyResponse = MutableStateFlow<WeeklyWeatherResponse?>(null)

    init {
        viewModelScope.launch {
            val startState = permissionsController.getPermissionState(Permission.LOCATION)
            println(startState)
            requestPermission(Permission.LOCATION)
            collectLocationData()
        }
    }

    private suspend fun requestPermission(coarseLocation: Permission) {
        try {
            permissionsController.providePermission(coarseLocation)
        } catch (deniedAlwaysException: DeniedAlwaysException) {
            Logger.d { deniedAlwaysException.message ?: "" }
        } catch (deniedException: DeniedException) {
            Logger.d { deniedException.message ?: "" }
        } finally {
            when (permissionsController.getPermissionState(coarseLocation)) {
                PermissionState.Denied, PermissionState.DeniedAlways -> permissionsController.openAppSettings()
                else -> {}
            }
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

    private suspend fun getWeeklyForecast(coordinates: Coordinates) {
        viewModelScope.launch(Dispatchers.Main) {
            kotlin.runCatching {
                val response = weatherApi.getWeeklyWeatherDetails(coordinates)
                weeklyResponse.update { response }
            }.getOrElse {
                state.emit(DashBoardScreenState.Error(it.cause?.message ?: "_ _"))
            }
        }
    }

    private suspend fun collectLocationData() {
        locationData.collectLatest {
            it?.let {
                collectWeatherData(it)
            }
        }
    }

    private suspend fun getWeather(locationData: Coordinates) {
        appSettings.weatherData.first()?.let {
            appSettings.updateWeatherData(it).also {
                getWeeklyForecast(locationData)
            }
            return
        }
        viewModelScope.launch(Dispatchers.Main) {
            kotlin.runCatching {
                val response = weatherApi.getWeatherInfo(locationData)
                appSettings.updateWeatherData(response).also {
                    getWeeklyForecast(locationData)
                }
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