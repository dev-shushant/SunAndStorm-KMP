package dev.shushant.sun_and_storm_kmp.designsystem.myiconpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import dev.shushant.sun_and_storm_kmp.designsystem.MyIconPack

internal val MyIconPack.IcBookmarks: ImageVector
    get() {
        if (_icBookmarks != null) {
            return _icBookmarks!!
        }
        _icBookmarks = Builder(name = "IcBookmarks", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF201A1B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(19.0f, 19.0f)
                curveTo(19.0f, 19.552f, 19.448f, 20.0f, 20.0f, 20.0f)
                curveTo(20.552f, 20.0f, 21.0f, 19.552f, 21.0f, 19.0f)
                verticalLineTo(3.0f)
                curveTo(21.0f, 1.9f, 20.1f, 1.0f, 19.0f, 1.0f)
                horizontalLineTo(7.0f)
                curveTo(6.448f, 1.0f, 6.0f, 1.448f, 6.0f, 2.0f)
                curveTo(6.0f, 2.552f, 6.448f, 3.0f, 7.0f, 3.0f)
                horizontalLineTo(19.0f)
                verticalLineTo(19.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF201A1B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(15.0f, 5.0f)
                horizontalLineTo(5.0f)
                curveTo(3.9f, 5.0f, 3.0f, 5.9f, 3.0f, 7.0f)
                verticalLineTo(21.483f)
                curveTo(3.0f, 22.201f, 3.734f, 22.685f, 4.394f, 22.403f)
                lineTo(10.0f, 20.0f)
                lineTo(15.606f, 22.403f)
                curveTo(16.266f, 22.685f, 17.0f, 22.201f, 17.0f, 21.483f)
                verticalLineTo(7.0f)
                curveTo(17.0f, 5.9f, 16.1f, 5.0f, 15.0f, 5.0f)
                close()
            }
        }
        .build()
        return _icBookmarks!!
    }

private var _icBookmarks: ImageVector? = null
