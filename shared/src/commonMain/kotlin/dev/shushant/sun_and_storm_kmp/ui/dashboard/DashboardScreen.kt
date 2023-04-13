package dev.shushant.sun_and_storm_kmp.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.getDimens
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.ui.viewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun DashboardScreen(
    permissionController: PermissionsController?,
) {
    val viewModel = viewModel(
        DashboardViewModel::class,
        listOf(permissionController)
    ) {
        DashboardViewModel(permissionController)
    }

    val state by viewModel.state.collectAsStateWithLifecycle(DashBoardScreenState.Loading)
    val weekly by viewModel.weeklyResponse.collectAsStateWithLifecycle(null)

    val time = GMTDate()
    val date = "${time.dayOfMonth} ${time.month.value}${time.year.toString().takeLast(2)}"
    WeeklyForecast(weekly) {
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

        }
    }
}
