package dev.shushant.sun_and_storm_kmp.screennavigation

sealed class Screen(val route: String) {
    object DashBoardScreen : Screen("DashBoardScreen")
    object AllPlacesScreen : Screen("AllPlacesScreen")
    object CreateLocationAlertScreen : Screen("CreateLocationAlertScreen")
}
