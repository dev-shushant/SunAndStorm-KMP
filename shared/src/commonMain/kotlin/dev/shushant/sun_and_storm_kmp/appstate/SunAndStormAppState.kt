package dev.shushant.sun_and_storm_kmp.appstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.shushant.sun_and_storm_kmp.screennavigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
internal fun rememberSunAndStormAppState(
    navigator: Navigator = rememberNavigator(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): SunAndStormAppState {
    return remember(coroutineScope, navigator) {
        SunAndStormAppState(coroutineScope, navigator)
    }
}

@Stable
internal class SunAndStormAppState(
    val coroutineScope: CoroutineScope,
    val navigator: Navigator
) {
    val currentDestination: String
        @Composable get() = navigator.currentEntry.collectAsState(null).value?.route?.route ?: ""


    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination) {
            TopLevelDestination.FOR_YOU.name -> TopLevelDestination.FOR_YOU
            TopLevelDestination.BOOKMARKS.name -> TopLevelDestination.BOOKMARKS
            TopLevelDestination.INTERESTS.name -> TopLevelDestination.INTERESTS
            else -> null
        }

    var shouldShowSettingsDialog by mutableStateOf(false)
        private set


    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = NavOptions(
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo = PopUpTo(topLevelDestination.name),
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item

        )
        navigator.navigate(topLevelDestination.name, topLevelNavOptions)
    }

    fun navigate(route: String, popUpTo: Boolean = false) {
        val navOptions = if (popUpTo) NavOptions(
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo = PopUpTo(route),
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item

        ) else null
        navigator.navigate(route, navOptions)
    }

    fun setShowSettingsDialog(shouldShow: Boolean) {
        shouldShowSettingsDialog = shouldShow
    }

    @Composable
    fun isTopLevelDestinationInHierarchy(destination: TopLevelDestination): Boolean {
        return currentDestination == destination.name
    }
}
