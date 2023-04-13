package dev.shushant.sun_and_storm_kmp.ui.searchplace

import dev.shushant.sun_and_storm_kmp.data.data.PlaceSearchResponseItem
import dev.shushant.sun_and_storm_kmp.network.WeatherApi
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel() :
    ViewModel(),
    KoinComponent {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val weatherApi: WeatherApi by inject()

    val state = MutableStateFlow<SearchScreenState?>(null)
    private var job: Job? = null
    private val appSettings: AppSettings by inject()

    fun search(place: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(500L)
            kotlin.runCatching {
                val result = weatherApi.getPlacesBySearch(place)
                state.emit(SearchScreenState.Success(places = result))
            }.getOrElse {
                state.emit(SearchScreenState.Error(it.message ?: ""))
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    fun userClicked(data: PlaceSearchResponseItem) {
        appSettings.updateSearchedList(data)
    }
}