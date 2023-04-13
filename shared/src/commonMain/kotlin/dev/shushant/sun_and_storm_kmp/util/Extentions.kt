package dev.shushant.sun_and_storm_kmp.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

fun Modifier.textBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }

fun getDate(): Pair<String, String> {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val startOfWeek =
        today.minus(DateTimeUnit.DayBased(today.dayOfWeek.ordinal - DayOfWeek.MONDAY.ordinal))
    val endOfWeek =
        today.plus(DateTimeUnit.DayBased(DayOfWeek.SUNDAY.ordinal - today.dayOfWeek.ordinal))

    return Pair(startOfWeek.toString(), endOfWeek.toString())
}

fun LocalDate.isToday() =
    this.toString() == Clock.System.todayIn(TimeZone.currentSystemDefault()).toString()