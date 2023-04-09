package dev.shushant.sun_and_storm_kmp.data.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

enum class WeatherIcons(val icon: String) {

}


@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun Int.getIcon(): Painter {
    return when (this) {
        0 -> {
            painterResource("clear_sky.png")
        }

        1, 2, 3 -> {// Mainly clear, partly cloudy, and overcast
            when (this) {
                1 -> {
                    painterResource("overcast.png")
                }

                2 -> {
                    painterResource("parttly_cloudy.png")
                }

                3 -> {
                    painterResource("cloudy.png")
                }

                else -> {
                    painterResource("clear_sky.png")
                }
            }
        }

        45, 48 -> {
            //Fog and depositing rime fog
            painterResource("clear_sky.png")
        }

        51, 53, 55 -> {
            //Drizzle: Light, moderate, and dense intensity
            painterResource("clear_sky.png")
        }

        56, 57 -> {
            //Freezing Drizzle: Light and dense intensity
            painterResource("clear_sky.png")
        }

        61, 63, 65 -> {
            //Rain: Slight, moderate and heavy intensity
            painterResource("clear_sky.png")
        }

        66, 67 -> {
            //Freezing Rain: Light and heavy intensity
            painterResource("clear_sky.png")
        }

        71, 73, 75 -> {
            //Snow fall: Slight, moderate, and heavy intensity
            painterResource("clear_sky.png")
        }

        77 -> {
            //Snow grains
            painterResource("clear_sky.png")
        }

        80, 81, 82 -> {
            //Rain showers: Slight, moderate, and violent
            painterResource("clear_sky.png")
        }

        85, 86 -> {
            //Snow showers slight and heavy
            painterResource("clear_sky.png")
        }

        95 -> {
            // Thunderstorm: Slight or moderate
            painterResource("clear_sky.png")
        }

        96, 99 -> {
            //Thunderstorm with slight and heavy hail
            painterResource("clear_sky.png")
        }

        else -> {
            painterResource("clear_sky.png")
        }
    }
}