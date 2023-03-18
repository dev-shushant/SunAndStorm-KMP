package dev.shushant.sun_and_storm_kmp.listing


sealed interface DashBoardScreenState {
    object Loading : DashBoardScreenState
    data class Error(val message: String) : DashBoardScreenState
    data class Success(
        val countriesList: List<Country>,
        val selectedCountry: Country,
        val selectedItemIndex: Int = 0,
    ) : DashBoardScreenState
}
