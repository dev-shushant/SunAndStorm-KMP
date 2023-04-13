package dev.shushant.sun_and_storm_kmp.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.shushant.sun_and_storm_kmp.data.data.getIcon
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.getDimens
import dev.shushant.sun_and_storm_kmp.util.textBrush
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun WeatherView(
    temperature: String,
    location: String,
    date: String,
    weatherCode: Int? = 0
) {
    Box(modifier = Modifier.height(300.getDimens).fillMaxWidth()) {
        Box(
            modifier = Modifier.background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF67E1D2), Color(0xFF54A8FF)
                    ),
                ), shape = RoundedCornerShape(41.7949f.getDimens)
            ).height(240.getDimens).fillMaxWidth().align(Alignment.BottomCenter),
        ) {
            Text(
                temperature,
                modifier = Modifier.align(Alignment.TopEnd).textBrush(
                    Brush.linearGradient(
                        listOf(
                            Color(0xFFFFFFFF), Color(0xcc54a8ff)
                        )
                    )
                ).padding(top = 50.getDimens, end = 15.getDimens),
                fontSize = 76.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                overflow = TextOverflow.Ellipsis
            )

            Column(modifier = Modifier.align(Alignment.BottomStart).padding(30.dp)) {
                Text(
                    location,
                    fontSize = 23.41.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "Today，$date",
                    fontSize = 19.23.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }

            Image(
                painterResource("wind.png"),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 25.getDimens)
            )
        }
        weatherCode?.getIcon()?.let {
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopStart).size(170.getDimens)
                    .padding(start = 10.getDimens)
            )
        }
    }
}