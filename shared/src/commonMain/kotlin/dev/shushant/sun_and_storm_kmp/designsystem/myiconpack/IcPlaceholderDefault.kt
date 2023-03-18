package dev.shushant.sun_and_storm_kmp.designsystem.myiconpack

import dev.shushant.sun_and_storm_kmp.designsystem.MyIconPack
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val MyIconPack.IcPlaceholderDefault: ImageVector
    get() {
        if (_icPlaceholderDefault != null) {
            return _icPlaceholderDefault!!
        }
        _icPlaceholderDefault = Builder(name = "IcPlaceholderDefault", defaultWidth = 364.0.dp,
                defaultHeight = 182.0.dp, viewportWidth = 364.0f, viewportHeight = 182.0f).apply {
            path(fill = SolidColor(Color(0xFFFCFCFC)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(364.0f)
                verticalLineToRelative(182.0f)
                horizontalLineToRelative(-364.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF7E7576)), stroke = null, fillAlpha = 0.02f,
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(364.0f)
                verticalLineToRelative(182.0f)
                horizontalLineToRelative(-364.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF8C4190)), stroke = null, fillAlpha = 0.11f,
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(364.0f)
                verticalLineToRelative(182.0f)
                horizontalLineToRelative(-364.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF5DD4FB)),
                    strokeLineWidth = 2.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(171.0f, 119.0f)
                lineTo(326.0f, 119.0f)
                arcTo(25.0f, 25.0f, 0.0f, false, true, 351.0f, 144.0f)
                lineTo(351.0f, 144.0f)
                arcTo(25.0f, 25.0f, 0.0f, false, true, 326.0f, 169.0f)
                lineTo(171.0f, 169.0f)
                arcTo(25.0f, 25.0f, 0.0f, false, true, 146.0f, 144.0f)
                lineTo(146.0f, 144.0f)
                arcTo(25.0f, 25.0f, 0.0f, false, true, 171.0f, 119.0f)
                close()
            }
            path(fill = linearGradient(0.0f to Color(0xFFFFA8FF), 1.0f to Color(0xFFFF8B5E), start =
                    Offset(224.0f,11.9999f), end = Offset(181.972f,138.703f)), stroke = null,
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = EvenOdd) {
                moveTo(156.023f, 33.705f)
                curveTo(154.247f, 37.971f, 153.333f, 42.543f, 153.333f, 47.161f)
                lineTo(188.666f, 47.161f)
                lineTo(224.0f, 47.161f)
                curveTo(224.0f, 42.543f, 223.086f, 37.971f, 221.31f, 33.705f)
                curveTo(219.535f, 29.44f, 216.932f, 25.563f, 213.651f, 22.298f)
                curveTo(210.37f, 19.033f, 206.475f, 16.444f, 202.188f, 14.677f)
                curveTo(197.901f, 12.91f, 193.307f, 12.0f, 188.667f, 12.0f)
                curveTo(184.026f, 12.0f, 179.432f, 12.91f, 175.145f, 14.677f)
                curveTo(170.858f, 16.444f, 166.963f, 19.033f, 163.682f, 22.298f)
                curveTo(160.401f, 25.563f, 157.798f, 29.44f, 156.023f, 33.705f)
                close()
                moveTo(153.333f, 47.161f)
                curveTo(153.333f, 51.778f, 152.409f, 56.35f, 150.615f, 60.616f)
                curveTo(148.821f, 64.882f, 146.192f, 68.758f, 142.877f, 72.023f)
                curveTo(139.562f, 75.288f, 135.627f, 77.878f, 131.296f, 79.645f)
                curveTo(126.965f, 81.412f, 122.323f, 82.322f, 117.635f, 82.322f)
                curveTo(112.947f, 82.322f, 108.305f, 81.412f, 103.974f, 79.645f)
                curveTo(99.643f, 77.878f, 95.708f, 75.288f, 92.393f, 72.023f)
                curveTo(89.078f, 68.758f, 86.449f, 64.882f, 84.655f, 60.616f)
                curveTo(82.861f, 56.35f, 81.938f, 51.778f, 81.938f, 47.161f)
                lineTo(117.635f, 47.161f)
                lineTo(153.333f, 47.161f)
                close()
                moveTo(12.0f, 47.161f)
                curveTo(12.0f, 42.543f, 12.904f, 37.971f, 14.661f, 33.705f)
                curveTo(16.419f, 29.439f, 18.994f, 25.563f, 22.242f, 22.298f)
                curveTo(25.489f, 19.033f, 29.344f, 16.443f, 33.586f, 14.676f)
                curveTo(37.829f, 12.909f, 42.376f, 12.0f, 46.968f, 12.0f)
                curveTo(51.561f, 12.0f, 56.108f, 12.909f, 60.351f, 14.676f)
                curveTo(64.593f, 16.443f, 68.448f, 19.033f, 71.695f, 22.298f)
                curveTo(74.942f, 25.563f, 77.518f, 29.439f, 79.276f, 33.705f)
                curveTo(81.033f, 37.971f, 81.938f, 42.543f, 81.938f, 47.161f)
                lineTo(46.968f, 47.161f)
                lineTo(12.0f, 47.161f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFFF8B5E)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Round,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(317.068f, 82.136f)
                lineTo(317.065f, 47.103f)
                lineTo(309.777f, 81.37f)
                lineTo(317.057f, 47.102f)
                lineTo(302.805f, 79.105f)
                lineTo(317.051f, 47.099f)
                lineTo(296.456f, 75.439f)
                lineTo(317.045f, 47.094f)
                lineTo(291.007f, 70.534f)
                lineTo(317.04f, 47.089f)
                lineTo(286.698f, 64.602f)
                lineTo(317.036f, 47.083f)
                lineTo(283.716f, 57.905f)
                lineTo(317.034f, 47.076f)
                lineTo(282.192f, 50.734f)
                lineTo(317.033f, 47.068f)
                lineTo(282.192f, 43.403f)
                lineTo(317.034f, 47.061f)
                lineTo(283.716f, 36.232f)
                lineTo(317.036f, 47.054f)
                lineTo(286.698f, 29.534f)
                lineTo(317.04f, 47.048f)
                lineTo(291.007f, 23.603f)
                lineTo(317.045f, 47.042f)
                lineTo(296.456f, 18.697f)
                lineTo(317.051f, 47.038f)
                lineTo(302.805f, 15.032f)
                lineTo(317.057f, 47.035f)
                lineTo(309.777f, 12.766f)
                lineTo(317.065f, 47.033f)
                lineTo(317.068f, 12.0f)
                lineTo(317.072f, 47.033f)
                lineTo(324.359f, 12.766f)
                lineTo(317.079f, 47.035f)
                lineTo(331.332f, 15.032f)
                lineTo(317.086f, 47.038f)
                lineTo(337.681f, 18.697f)
                lineTo(317.092f, 47.042f)
                lineTo(343.129f, 23.603f)
                lineTo(317.097f, 47.048f)
                lineTo(347.438f, 29.534f)
                lineTo(317.1f, 47.054f)
                lineTo(350.42f, 36.232f)
                lineTo(317.102f, 47.061f)
                lineTo(351.944f, 43.403f)
                lineTo(317.103f, 47.068f)
                lineTo(351.944f, 50.734f)
                lineTo(317.102f, 47.076f)
                lineTo(350.42f, 57.905f)
                lineTo(317.1f, 47.083f)
                lineTo(347.438f, 64.602f)
                lineTo(317.097f, 47.089f)
                lineTo(343.129f, 70.534f)
                lineTo(317.092f, 47.094f)
                lineTo(337.681f, 75.439f)
                lineTo(317.086f, 47.099f)
                lineTo(331.332f, 79.105f)
                lineTo(317.079f, 47.102f)
                lineTo(324.359f, 81.37f)
                lineTo(317.072f, 47.103f)
                lineTo(317.068f, 82.136f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFA8FF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(38.205f, 170.0f)
                lineTo(38.975f, 170.0f)
                arcTo(26.205f, 26.205f, 89.056f, false, false, 65.18f, 143.795f)
                lineTo(65.18f, 143.795f)
                arcTo(26.205f, 26.205f, 89.056f, false, false, 38.975f, 117.59f)
                lineTo(38.205f, 117.59f)
                arcTo(26.205f, 26.205f, 89.056f, false, false, 12.0f, 143.795f)
                lineTo(12.0f, 143.795f)
                arcTo(26.205f, 26.205f, 89.056f, false, false, 38.205f, 170.0f)
                close()
            }
        }
        .build()
        return _icPlaceholderDefault!!
    }

private var _icPlaceholderDefault: ImageVector? = null
