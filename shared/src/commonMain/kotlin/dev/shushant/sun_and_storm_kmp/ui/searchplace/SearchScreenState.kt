package dev.shushant.sun_and_storm_kmp.ui.searchplace

import dev.shushant.sun_and_storm_kmp.data.data.PlaceSearchResponseItem

sealed interface SearchScreenState {
    object Loading : SearchScreenState
    data class Error(val message: String) : SearchScreenState
    data class Success(
        val places: List<PlaceSearchResponseItem> = emptyList()
    ) : SearchScreenState
}