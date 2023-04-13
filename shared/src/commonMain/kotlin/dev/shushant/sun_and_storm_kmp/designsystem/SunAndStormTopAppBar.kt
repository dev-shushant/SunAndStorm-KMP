package dev.shushant.sun_and_storm_kmp.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.shushant.sun_and_storm_kmp.util.textBrush
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
internal fun SunAndStormTopAppBar(
    titleRes: String,
    isTopLevel: Boolean,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {},
    onAllLocation: () -> Unit = {},
    onNavigation: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = titleRes, modifier
                = Modifier.textBrush(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFF67E1D2),
                            Color(0xcc54a8ff)
                        )
                    )
                )
            )
        },
        actions = {
            if (isTopLevel) {
                IconButton(
                    onSearch,
                    modifier = Modifier.background(shape = CircleShape, brush = gradient)
                        .padding(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier.testTag("sunAndStormTopAppBar").padding(horizontal = 10.dp),
        navigationIcon = {
            if (isTopLevel) {
                IconButton(
                    onAllLocation,
                    modifier = Modifier.background(shape = CircleShape, brush = gradient)
                        .padding(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.FormatListBulleted,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            } else {
                IconButton(
                    onNavigation,
                    modifier = Modifier.background(shape = CircleShape, brush = gradient)
                        .padding(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    )
}

val gradient =
    Brush.horizontalGradient(listOf(Color(0xFF67E1D2), Color(0xcc54a8ff)))