package dev.shushant.sun_and_storm_kmp.designsystem.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val Location: ImageVector
    get() {
        if (_location != null) {
            return _location!!
        }
        _location = ImageVector.Builder(
            name = "Location", defaultWidth = 44.0.dp, defaultHeight = 44.0.dp,
            viewportWidth = 44.0f, viewportHeight = 44.0f
        ).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.9609f, 14.499f)
                curveTo(17.9609f, 16.3896f, 19.2393f, 17.9688f, 20.9902f, 18.4092f)
                verticalLineTo(25.209f)
                curveTo(20.9902f, 28.3779f, 21.5596f, 30.1074f, 21.9893f, 30.1074f)
                curveTo(22.4297f, 30.1074f, 22.9883f, 28.3887f, 22.9883f, 25.209f)
                verticalLineTo(18.4092f)
                curveTo(24.7393f, 17.9795f, 26.0283f, 16.3896f, 26.0283f, 14.499f)
                curveTo(26.0283f, 12.2754f, 24.2344f, 10.4492f, 21.9893f, 10.4492f)
                curveTo(19.7549f, 10.4492f, 17.9609f, 12.2754f, 17.9609f, 14.499f)
                close()
                moveTo(20.8398f, 14.7246f)
                curveTo(20.1094f, 14.7246f, 19.4648f, 14.0801f, 19.4648f, 13.3281f)
                curveTo(19.4648f, 12.5869f, 20.1094f, 11.9531f, 20.8398f, 11.9531f)
                curveTo(21.6025f, 11.9531f, 22.2256f, 12.5869f, 22.2256f, 13.3281f)
                curveTo(22.2256f, 14.0801f, 21.6025f, 14.7246f, 20.8398f, 14.7246f)
                close()
                moveTo(22.0f, 34.0391f)
                curveTo(28.4775f, 34.0391f, 32.1943f, 31.8047f, 32.1943f, 29.4414f)
                curveTo(32.1943f, 26.6055f, 27.6934f, 24.876f, 24.7393f, 24.8438f)
                verticalLineTo(26.4121f)
                curveTo(26.8125f, 26.4443f, 30.0244f, 27.5723f, 30.0244f, 29.1836f)
                curveTo(30.0244f, 31.0312f, 26.6191f, 32.3418f, 22.0f, 32.3418f)
                curveTo(17.3594f, 32.3418f, 13.9756f, 31.0527f, 13.9756f, 29.1836f)
                curveTo(13.9756f, 27.5723f, 17.1768f, 26.4443f, 19.25f, 26.4121f)
                verticalLineTo(24.8438f)
                curveTo(16.2959f, 24.876f, 11.7949f, 26.6055f, 11.7949f, 29.4414f)
                curveTo(11.7949f, 31.8047f, 15.5225f, 34.0391f, 22.0f, 34.0391f)
                close()
            }
        }
        .build()
        return _location!!
    }

private var _location: ImageVector? = null