package dev.shushant.sun_and_storm_kmp.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.shushant.sun_and_storm_kmp.data.data.WeeklyWeatherResponse
import dev.shushant.sun_and_storm_kmp.data.data.getIcon
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.getColor
import dev.shushant.sun_and_storm_kmp.designsystem.dimens.getDimens
import dev.shushant.sun_and_storm_kmp.util.isToday
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun WeeklyForecast(
    weeklyWeatherResponse: WeeklyWeatherResponse?, content: @Composable () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { false },
        skipHalfExpanded = false
    )

    val lazyState = rememberLazyListState()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            modalSheetState.show()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        scrimColor = Color.Transparent,
        sheetShape = RoundedCornerShape(
            topStart = 41.7949f.getDimens, topEnd = 41.7949f.getDimens,
        ),
        sheetElevation = 2.dp,
        modifier = Modifier.fillMaxSize(),
        sheetContent = {
            LazyRow(
                modifier = Modifier.background(
                    Brush.verticalGradient(
                        listOf(
                            Color(
                                0x3366e0d1
                            ), Color(0xFFF5F5F5)
                        )
                    ), shape = RoundedCornerShape(41.7949f.getDimens)
                ).border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(41.7949f.getDimens)
                ).height(265.88f.getDimens),
                horizontalArrangement = Arrangement.spacedBy(10.getDimens),
                state = lazyState,
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(
                    weeklyWeatherResponse?.daily?.time ?: emptyList()
                ) { index, item ->
                    val date = LocalDate.parse(item ?: "")
                    val isToday = date.isToday()
                    if (isToday) {
                        TodayItem(
                            WeeklyData(
                                date = date.dayOfMonth.toString(),
                                dayOfWeek = date.dayOfWeek.name.take(3),
                                aqi = "100",
                                temp = weeklyWeatherResponse?.daily?.temperature2mMax?.get(index)
                                    ?.roundToInt()
                                    .toString().plus("\u2103"),
                                weatherCode = weeklyWeatherResponse?.daily?.weathercode?.get(index)
                                    ?: 0
                            )
                        )
                        LaunchedEffect(Unit) {
                            coroutineScope.launch {
                                delay(1000L)
                                lazyState.animateScrollToItem(index - 1)
                            }
                        }
                    } else OtherItem(
                        WeeklyData(
                            date = date.dayOfMonth.toString().plus("/${date.month.ordinal}"),
                            dayOfWeek = date.dayOfWeek.name.take(3),
                            aqi = "100",
                            temp = weeklyWeatherResponse?.daily?.temperature2mMax?.get(index)
                                ?.roundToInt()
                                .toString().plus("\u2103"),
                            weatherCode = weeklyWeatherResponse?.daily?.weathercode?.get(index) ?: 0
                        )
                    )
                }
            }
        },
        sheetBackgroundColor = Color.Transparent,
        sheetContentColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        content()
    }

}

@Composable
internal fun TodayItem(weeklyData: WeeklyData) {
    Column(
        modifier = Modifier.wrapContentHeight().background(
            Brush.linearGradient(
                listOf(
                    Color(0xFF67E1D2), Color(0xFF54A8FF)
                ),
            ), shape = RoundedCornerShape(41.7949f.getDimens)
        ).wrapContentWidth().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            weeklyData.dayOfWeek,
            fontSize = 17.55.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Text(
            weeklyData.date,
            fontSize = 15.05.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0XFFD0F3FF)
        )
        Image(
            painter = weeklyData.weatherCode.getIcon(),
            contentDescription = null,
            modifier = Modifier.size(45.getDimens),
            contentScale = ContentScale.Inside
            //.padding(10.getDimens)
        )
        Text(
            weeklyData.temp, fontSize = 23.41.sp, fontWeight = FontWeight.Bold, color = Color.White
        )
        Text(
            weeklyData.aqi,
            fontSize = 15.05.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.background(
                shape = RoundedCornerShape(8.30.dp), color = 100.getColor
            ).padding(5.dp)
        )
    }
}

@Composable
internal fun OtherItem(weeklyData: WeeklyData) {
    Column(
        modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            weeklyData.dayOfWeek,
            fontSize = 17.55.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Text(
            weeklyData.date,
            fontSize = 15.05.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0XFFA0A7BA)
        )
        Image(
            painter = weeklyData.weatherCode.getIcon(),
            contentDescription = null,
            modifier = Modifier.size(45.getDimens),
            contentScale = ContentScale.Inside
            //.padding(10.getDimens)
        )
        Text(
            "22℃", fontSize = 23.41.sp, fontWeight = FontWeight.Bold, color = Color(0XFF29292C)
        )
        Text(
            weeklyData.aqi,
            fontSize = 15.05.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.background(
                shape = RoundedCornerShape(8.30.dp), color = 100.getColor
            ).padding(5.dp)
        )
    }
}

data class WeeklyData(
    val dayOfWeek: String, val date: String, val temp: String, val aqi: String, val weatherCode: Int
)