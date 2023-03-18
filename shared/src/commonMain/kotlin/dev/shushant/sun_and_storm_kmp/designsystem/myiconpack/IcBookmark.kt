package dev.shushant.sun_and_storm_kmp.designsystem.myiconpack

import dev.shushant.sun_and_storm_kmp.designsystem.MyIconPack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val MyIconPack.IcBookmark: ImageVector
    get() {
        if (_icBookmark != null) {
            return _icBookmark!!
        }
        _icBookmark = Builder(name = "IcBookmark", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF201A1B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(17.0f, 3.0f)
                horizontalLineTo(7.0f)
                curveTo(5.9f, 3.0f, 5.0f, 3.9f, 5.0f, 5.0f)
                verticalLineTo(19.483f)
                curveTo(5.0f, 20.201f, 5.734f, 20.685f, 6.394f, 20.403f)
                lineTo(12.0f, 18.0f)
                lineTo(17.606f, 20.403f)
                curveTo(18.266f, 20.685f, 19.0f, 20.201f, 19.0f, 19.483f)
                verticalLineTo(5.0f)
                curveTo(19.0f, 3.9f, 18.1f, 3.0f, 17.0f, 3.0f)
                close()
            }
        }
        .build()
        return _icBookmark!!
    }

private var _icBookmark: ImageVector? = null
