package dev.shushant.sun_and_storm_kmp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.shushant.sun_and_storm_kmp.CurrentPlatform
import dev.shushant.sun_and_storm_kmp.data.DashBoardScreenState
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.getDimens
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.ui.viewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun DashboardScreen() {
    val viewModel = viewModel(DashboardViewModel::class) {
        DashboardViewModel()
    }
    viewModel.platform = CurrentPlatform.current.value

    val state by viewModel.state.collectAsStateWithLifecycle(DashBoardScreenState.Loading)

    val time = GMTDate()
    val date = "${time.dayOfMonth} ${time.month.value}${time.year.toString().takeLast(2)}"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(27.getDimens),
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.getDimens)
            .verticalScroll(
                rememberScrollState()
            ),
    ) {
        val location: String
        val temperature: String
        when (state) {
            is DashBoardScreenState.Loading -> {
                location = "Loading..."
                temperature = "--".plus("\u2103")
                WeatherView(
                    temperature,
                    location,
                    date,
                )
            }

            is DashBoardScreenState.Error -> {
                location = (state as DashBoardScreenState.Error).message
                temperature = "--".plus("\u2103")
                WeatherView(
                    temperature,
                    location,
                    date,
                )
            }

            is DashBoardScreenState.Success -> {
                val response = (state as DashBoardScreenState.Success)
                location = response.locationData.address.subLocality
                temperature =
                    "${response.weatherData.currentWeather?.temperature?.roundToInt()}".plus(
                        "\u2103"
                    )
                WeatherView(
                    temperature,
                    location,
                    date,
                    response.weatherData.currentWeather?.weathercode
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(285.88.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(
                                0x7766E0D1
                            ), Color(0xFFF5F5F5)
                        )
                    ), shape = RoundedCornerShape(41.7949f.getDimens)
                )
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(41.7949f.getDimens)
                )
                .offset(x = 25.08f.getDimens, y = 679.58f.getDimens)
                .rotate(-90f)
        ) {

        }
    }
}
