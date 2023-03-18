package dev.shushant.sun_and_storm_kmp.designsystem

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.shushant.sun_and_storm_kmp.screennavigation.TopLevelDestination
import dev.shushant.sun_and_storm_kmp.style.SunAndStormNavigationDefaults

@Composable
internal fun SunAndStormNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: String,
    modifier: Modifier
) {
    NiaNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination == destination.name
            NiaNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        imageVector = icon.imageVector,
                        contentDescription = null,
                    )
                },
                label = { Text(destination.iconTextId) },
            )
        }
    }
}

@Composable
internal fun NiaNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = SunAndStormNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

@Composable
internal fun NiaNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = SunAndStormNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = SunAndStormNavigationDefaults.navigationContentColor(),
            selectedTextColor = SunAndStormNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = SunAndStormNavigationDefaults.navigationContentColor(),
            indicatorColor = SunAndStormNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}