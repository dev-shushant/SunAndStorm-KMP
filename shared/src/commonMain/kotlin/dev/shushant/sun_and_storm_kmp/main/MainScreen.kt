package dev.shushant.sun_and_storm_kmp.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.shushant.sun_and_storm_kmp.CurrentPlatform
import dev.shushant.sun_and_storm_kmp.Platform
import dev.shushant.sun_and_storm_kmp.SafeArea
import dev.shushant.sun_and_storm_kmp.appstate.SunAndStormAppState
import dev.shushant.sun_and_storm_kmp.appstate.rememberSunAndStormAppState
import dev.shushant.sun_and_storm_kmp.designsystem.SunAndStormIcon
import dev.shushant.sun_and_storm_kmp.designsystem.SunAndStormIcon.LocationIcon
import dev.shushant.sun_and_storm_kmp.designsystem.SunAndStormNavRail
import dev.shushant.sun_and_storm_kmp.designsystem.SunAndStormTopAppBar
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.BlurBackground
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.Hover
import dev.shushant.sun_and_storm_kmp.screennavigation.Screen
import dev.shushant.sun_and_storm_kmp.screennavigation.settingsR
import dev.shushant.sun_and_storm_kmp.style.Dimens.NavigationBarHeight
import dev.shushant.sun_and_storm_kmp.ui.dashboard.DashboardScreen
import moe.tlaster.precompose.navigation.NavHost
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun Dummy(appState: SunAndStormAppState) {
    Text(appState.currentDestination)
}

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class, ExperimentalResourceApi::class
)
@Composable
internal fun MainScreen(
    showNavRail: Boolean,
    modifier: Modifier,
    appState: SunAndStormAppState = rememberSunAndStormAppState(),
) {
    val currentPlatform = CurrentPlatform.current.value
    val shouldShowBottomBar = false //showNavRail.not()

    Scaffold(containerColor = Color.Transparent,
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            Box(Modifier.fillMaxWidth()) {
                if (shouldShowBottomBar) {
                    Image(
                        painter = painterResource("bottom_bar.png"),
                        "",
                        modifier = Modifier.fillMaxWidth()
                            .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                            .height(NavigationBarHeight).align(Alignment.BottomCenter),
                        contentScale = ContentScale.FillBounds
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val iconModifier = when (currentPlatform) {
                            Platform.IOS -> Modifier
                            Platform.ANDROID -> Modifier.padding(top = 10.dp)
                            Platform.WEB -> Modifier
                            Platform.DESKTOP -> Modifier.padding(top = 20.dp)
                        }
                        Image(imageVector = LocationIcon,
                            contentDescription = null,
                            modifier = iconModifier.clickable {
                                appState.navigate(Screen.DashBoardScreen.route, true)
                            })

                        Image(
                            painter = painterResource("home.png"),
                            "",
                            modifier = Modifier.width(220.dp)
                                .windowInsetsPadding(NavigationBarDefaults.windowInsets).clickable {
                                    appState.navigate(
                                        Screen.CreateLocationAlertScreen.route, true
                                    )
                                },
                            contentScale = ContentScale.Inside
                        )

                        Image(imageVector = Hover,
                            contentDescription = null,
                            modifier = iconModifier.clickable {
                                appState.navigate(Screen.AllPlacesScreen.route, true)
                            })
                    }
                }
            }
        }) { padding ->
        Row(
            Modifier.fillMaxSize().padding(padding).padding(
                top = SafeArea.current.value.calculateTopPadding()
            ).consumedWindowInsets(padding)
        ) {
            if (showNavRail) {
                SunAndStormNavRail(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                    
                    .
                )
            }
            Column(Modifier.fillMaxSize()) {
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    SunAndStormTopAppBar(
                        titleRes = destination.titleTextId,
                        actionIcon = SunAndStormIcon.Settings,
                        actionIconContentDescription = settingsR,
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                        onActionClick = { appState.setShowSettingsDialog(true) },
                    )
                }
                NavHost(
                    navigator = appState.navigator,
                    initialRoute = Screen.DashBoardScreen.route,
                ) {
                    scene(Screen.DashBoardScreen.route) {
                        DashboardScreen(appState)
                    }

                    scene(Screen.AllPlacesScreen.route) {
                        Dummy(appState)
                    }
                    scene(Screen.CreateLocationAlertScreen.route) {
                        Dummy(appState)
                    }

                }
            }
        }
    }
}
