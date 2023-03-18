package dev.shushant.sun_and_storm_kmp.listing

import dev.shushant.sun_and_storm_kmp.network.CountriesApiImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DashBoardViewModel {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    private val countriesApi = CountriesApiImpl()
    val state = MutableStateFlow<DashBoardScreenState>(DashBoardScreenState.Loading)

    init {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val countriesList = countriesApi.getCountriesList()
                state.emit(
                    DashBoardScreenState.Success(
                        countriesList = countriesList,
                        selectedCountry = countriesList.first(),
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                state.emit(DashBoardScreenState.Error(e.message ?: "Something went wrong"))
            }
        }
    }

    fun onAction(actions: ListViewModelActions) {
        viewModelScope.launch {
            when (actions) {
                is ListViewModelActions.OnCountrySelected -> {
                    emitNewState(actions)
                }

                is ListViewModelActions.OnItemSwiped -> {
                    state.emit(
                        (state.value as DashBoardScreenState.Success).copy(
                            selectedItemIndex = actions.index
                        )
                    )
                }

                is ListViewModelActions.MoveToIndex -> {
                    val previousState = (state.value as DashBoardScreenState.Success)
                    state.emit(
                        previousState.copy(
                            selectedItemIndex = actions.index,
                        )
                    )
                }
            }
        }
    }

    private suspend fun emitNewState(actions: ListViewModelActions.OnCountrySelected) {
        getStateValueWithEmptyState(state.value)?.run {
            val country = this.countriesList.first { it.name == actions.country.name }
            val latestState = this.copy(
                selectedCountry = country,
                selectedItemIndex = 0,
            )
            state.emit(latestState)
        }
    }

    private fun getStateValueWithEmptyState(state: DashBoardScreenState): DashBoardScreenState.Success? {
        return when (state) {
            is DashBoardScreenState.Error -> null
            DashBoardScreenState.Loading -> null
            is DashBoardScreenState.Success -> state
        }
    }
}


sealed interface ListViewModelActions {
    data class OnCountrySelected(val country: Country) : ListViewModelActions
    data class MoveToIndex(val index: Int) : ListViewModelActions
    data class OnItemSwiped(val touristPlace: TouristPlace, val index: Int) : ListViewModelActions
}