package dev.shushant.sun_and_storm_kmp.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.shushant.sun_and_storm_kmp.SafeArea
import dev.shushant.sun_and_storm_kmp.appstate.SunAndStormAppState
import dev.shushant.sun_and_storm_kmp.appstate.rememberSunAndStormAppState
import dev.shushant.sun_and_storm_kmp.designsystem.SunAndStormTopAppBar
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.getDimens
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController
import dev.shushant.sun_and_storm_kmp.screennavigation.Screen
import dev.shushant.sun_and_storm_kmp.ui.dashboard.DashboardScreen
import moe.tlaster.precompose.navigation.NavHost
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalResourceApi::class
)
@Composable
internal fun MainScreen(
    modifier: Modifier,
    permissionController: PermissionsController,
    appState: SunAndStormAppState = rememberSunAndStormAppState(),
) {
    Scaffold(containerColor = Color.Transparent,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {

        }) { padding ->
        Row(
            Modifier.fillMaxSize().padding(padding).padding(
                top = SafeArea.current.value.calculateTopPadding() + 20.getDimens
            ).consumedWindowInsets(padding)
        ) {
            Column(Modifier.fillMaxSize()) {
                SunAndStormTopAppBar(
                    titleRes = "SunAndStorm",
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                ) { appState.setShowSettingsDialog(true) }
                NavHost(
                    navigator = appState.navigator,
                    initialRoute = Screen.DashBoardScreen.route,
                ) {
                    scene(Screen.DashBoardScreen.route) {
                        DashboardScreen(permissionController)
                    }
                }
            }
        }
    }
}
