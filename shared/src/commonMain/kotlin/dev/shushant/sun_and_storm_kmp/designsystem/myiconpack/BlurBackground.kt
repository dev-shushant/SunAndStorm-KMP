package dev.shushant.sun_and_storm_kmp.designsystem.myiconpack

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val BlurBackground: ImageVector
    get() {
        if (_blurBackground != null) {
            return _blurBackground!!
        }
        _blurBackground = Builder(
            name = "BlurBackground", defaultWidth = 389.0.dp, defaultHeight =
            325.0.dp, viewportWidth = 389.0f, viewportHeight = 325.0f
        ).apply {
            path(
                fill = linearGradient(
                    0.0f to Color(0xFF2E335A), 1.0f to Color(0xFF1C1B33), start =
                    Offset(49.0f, -1.34697E-6f), end = Offset(195.0f, 325.0f)
                ), stroke = null,
                fillAlpha = 0.2f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin =
                Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 44.0f)
                curveTo(0.0f, 19.699f, 19.699f, 0.0f, 44.0f, 0.0f)
                horizontalLineTo(346.0f)
                curveTo(370.301f, 0.0f, 390.0f, 19.699f, 390.0f, 44.0f)
                verticalLineTo(325.0f)
                horizontalLineTo(0.0f)
                verticalLineTo(44.0f)
                close()
            }
        }
            .build()
        return _blurBackground!!
    }

private var _blurBackground: ImageVector? = null
