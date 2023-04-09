package dev.shushant.sun_and_storm_kmp.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

internal val Night: ImageVector
    get() {
        if (_night != null) {
            return _night!!
        }
        _night = Builder(
            name = "Night", defaultWidth = 135.0.dp, defaultHeight = 128.0.dp,
            viewportWidth = 135.0f, viewportHeight = 128.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF73E6FF)), stroke = null, fillAlpha = 0.37f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(27.808f, 20.108f)
                lineTo(82.141f, 20.108f)
                arcTo(27.167f, 27.167f, 0.0f, false, true, 109.308f, 47.275f)
                lineTo(109.308f, 47.275f)
                arcTo(27.167f, 27.167f, 0.0f, false, true, 82.141f, 74.441f)
                lineTo(27.808f, 74.441f)
                arcTo(27.167f, 27.167f, 0.0f, false, true, 0.641f, 47.275f)
                lineTo(0.641f, 47.275f)
                arcTo(27.167f, 27.167f, 0.0f, false, true, 27.808f, 20.108f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF7EB9C5)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(81.932f, 63.784f)
                moveToRelative(-21.524f, 0.0f)
                arcToRelative(21.524f, 21.524f, 0.0f, true, true, 43.049f, 0.0f)
                arcToRelative(21.524f, 21.524f, 0.0f, true, true, -43.049f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(25.3f, 53.491f)
                curveTo(28.734f, 53.491f, 31.517f, 50.708f, 31.517f, 47.274f)
                curveTo(31.517f, 43.841f, 28.734f, 41.057f, 25.3f, 41.057f)
                curveTo(21.867f, 41.057f, 19.083f, 43.841f, 19.083f, 47.274f)
                curveTo(19.083f, 50.708f, 21.867f, 53.491f, 25.3f, 53.491f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(25.3f, 39.595f)
                curveTo(25.494f, 39.595f, 25.68f, 39.518f, 25.817f, 39.38f)
                curveTo(25.954f, 39.243f, 26.031f, 39.057f, 26.031f, 38.863f)
                verticalLineTo(38.132f)
                curveTo(26.031f, 37.938f, 25.954f, 37.752f, 25.817f, 37.615f)
                curveTo(25.68f, 37.477f, 25.494f, 37.4f, 25.3f, 37.4f)
                curveTo(25.106f, 37.4f, 24.92f, 37.477f, 24.783f, 37.615f)
                curveTo(24.646f, 37.752f, 24.569f, 37.938f, 24.569f, 38.132f)
                verticalLineTo(38.863f)
                curveTo(24.569f, 39.057f, 24.646f, 39.243f, 24.783f, 39.38f)
                curveTo(24.92f, 39.518f, 25.106f, 39.595f, 25.3f, 39.595f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(18.836f, 41.844f)
                curveTo(18.975f, 41.978f, 19.16f, 42.054f, 19.353f, 42.054f)
                curveTo(19.546f, 42.054f, 19.731f, 41.978f, 19.869f, 41.844f)
                curveTo(20.006f, 41.707f, 20.083f, 41.521f, 20.083f, 41.327f)
                curveTo(20.083f, 41.133f, 20.006f, 40.948f, 19.869f, 40.811f)
                lineTo(19.348f, 40.29f)
                curveTo(19.209f, 40.163f, 19.027f, 40.096f, 18.839f, 40.1f)
                curveTo(18.651f, 40.105f, 18.473f, 40.182f, 18.34f, 40.314f)
                curveTo(18.207f, 40.447f, 18.131f, 40.626f, 18.126f, 40.813f)
                curveTo(18.121f, 41.001f, 18.189f, 41.183f, 18.315f, 41.323f)
                lineTo(18.836f, 41.844f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(16.889f, 46.543f)
                horizontalLineTo(16.157f)
                curveTo(15.963f, 46.543f, 15.777f, 46.62f, 15.64f, 46.757f)
                curveTo(15.503f, 46.894f, 15.426f, 47.08f, 15.426f, 47.274f)
                curveTo(15.426f, 47.468f, 15.503f, 47.654f, 15.64f, 47.792f)
                curveTo(15.777f, 47.929f, 15.963f, 48.006f, 16.157f, 48.006f)
                horizontalLineTo(16.889f)
                curveTo(17.083f, 48.006f, 17.269f, 47.929f, 17.406f, 47.792f)
                curveTo(17.543f, 47.654f, 17.62f, 47.468f, 17.62f, 47.274f)
                curveTo(17.62f, 47.08f, 17.543f, 46.894f, 17.406f, 46.757f)
                curveTo(17.269f, 46.62f, 17.083f, 46.543f, 16.889f, 46.543f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(18.836f, 52.705f)
                lineTo(18.315f, 53.226f)
                curveTo(18.178f, 53.363f, 18.101f, 53.549f, 18.101f, 53.743f)
                curveTo(18.101f, 53.936f, 18.178f, 54.122f, 18.315f, 54.259f)
                curveTo(18.455f, 54.394f, 18.642f, 54.469f, 18.836f, 54.47f)
                curveTo(19.028f, 54.469f, 19.212f, 54.394f, 19.348f, 54.259f)
                lineTo(19.869f, 53.738f)
                curveTo(19.995f, 53.599f, 20.063f, 53.417f, 20.059f, 53.229f)
                curveTo(20.054f, 53.041f, 19.977f, 52.863f, 19.844f, 52.73f)
                curveTo(19.712f, 52.597f, 19.533f, 52.521f, 19.345f, 52.516f)
                curveTo(19.158f, 52.511f, 18.975f, 52.579f, 18.836f, 52.705f)
                verticalLineTo(52.705f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(25.3f, 54.954f)
                curveTo(25.106f, 54.954f, 24.92f, 55.031f, 24.783f, 55.168f)
                curveTo(24.646f, 55.306f, 24.569f, 55.492f, 24.569f, 55.686f)
                verticalLineTo(56.417f)
                curveTo(24.569f, 56.611f, 24.646f, 56.797f, 24.783f, 56.934f)
                curveTo(24.92f, 57.071f, 25.106f, 57.148f, 25.3f, 57.148f)
                curveTo(25.494f, 57.148f, 25.68f, 57.071f, 25.817f, 56.934f)
                curveTo(25.954f, 56.797f, 26.031f, 56.611f, 26.031f, 56.417f)
                verticalLineTo(55.686f)
                curveTo(26.031f, 55.492f, 25.954f, 55.306f, 25.817f, 55.168f)
                curveTo(25.68f, 55.031f, 25.494f, 54.954f, 25.3f, 54.954f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(31.764f, 52.705f)
                curveTo(31.625f, 52.579f, 31.442f, 52.511f, 31.255f, 52.516f)
                curveTo(31.067f, 52.521f, 30.888f, 52.597f, 30.756f, 52.73f)
                curveTo(30.623f, 52.863f, 30.546f, 53.041f, 30.542f, 53.229f)
                curveTo(30.537f, 53.417f, 30.605f, 53.599f, 30.731f, 53.738f)
                lineTo(31.252f, 54.259f)
                curveTo(31.388f, 54.394f, 31.572f, 54.469f, 31.764f, 54.47f)
                curveTo(31.958f, 54.469f, 32.145f, 54.394f, 32.285f, 54.259f)
                curveTo(32.422f, 54.122f, 32.499f, 53.936f, 32.499f, 53.743f)
                curveTo(32.499f, 53.549f, 32.422f, 53.363f, 32.285f, 53.226f)
                lineTo(31.764f, 52.705f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(34.443f, 46.543f)
                horizontalLineTo(33.711f)
                curveTo(33.517f, 46.543f, 33.331f, 46.62f, 33.194f, 46.757f)
                curveTo(33.057f, 46.894f, 32.98f, 47.08f, 32.98f, 47.274f)
                curveTo(32.98f, 47.468f, 33.057f, 47.654f, 33.194f, 47.792f)
                curveTo(33.331f, 47.929f, 33.517f, 48.006f, 33.711f, 48.006f)
                horizontalLineTo(34.443f)
                curveTo(34.637f, 48.006f, 34.823f, 47.929f, 34.96f, 47.792f)
                curveTo(35.097f, 47.654f, 35.174f, 47.468f, 35.174f, 47.274f)
                curveTo(35.174f, 47.08f, 35.097f, 46.894f, 34.96f, 46.757f)
                curveTo(34.823f, 46.62f, 34.637f, 46.543f, 34.443f, 46.543f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(31.252f, 42.054f)
                curveTo(31.347f, 42.055f, 31.441f, 42.036f, 31.529f, 42.0f)
                curveTo(31.617f, 41.964f, 31.697f, 41.911f, 31.764f, 41.843f)
                lineTo(32.285f, 41.322f)
                curveTo(32.358f, 41.256f, 32.418f, 41.175f, 32.459f, 41.085f)
                curveTo(32.5f, 40.995f, 32.523f, 40.897f, 32.525f, 40.798f)
                curveTo(32.528f, 40.699f, 32.51f, 40.601f, 32.473f, 40.509f)
                curveTo(32.436f, 40.417f, 32.381f, 40.333f, 32.311f, 40.263f)
                curveTo(32.241f, 40.193f, 32.158f, 40.138f, 32.065f, 40.101f)
                curveTo(31.973f, 40.064f, 31.875f, 40.047f, 31.776f, 40.049f)
                curveTo(31.677f, 40.051f, 31.579f, 40.074f, 31.489f, 40.115f)
                curveTo(31.399f, 40.157f, 31.318f, 40.216f, 31.252f, 40.289f)
                lineTo(30.731f, 40.81f)
                curveTo(30.594f, 40.947f, 30.517f, 41.133f, 30.517f, 41.327f)
                curveTo(30.517f, 41.521f, 30.594f, 41.706f, 30.731f, 41.843f)
                curveTo(30.799f, 41.912f, 30.88f, 41.966f, 30.97f, 42.002f)
                curveTo(31.059f, 42.038f, 31.155f, 42.056f, 31.252f, 42.054f)
                verticalLineTo(42.054f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(82.141f, 47.275f)
                moveToRelative(-27.167f, 0.0f)
                arcToRelative(27.167f, 27.167f, 0.0f, true, true, 54.333f, 0.0f)
                arcToRelative(27.167f, 27.167f, 0.0f, true, true, -54.333f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF5AB9F3)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(91.889f, 49.532f)
                curveTo(91.837f, 49.364f, 91.732f, 49.217f, 91.589f, 49.113f)
                curveTo(91.447f, 49.009f, 91.275f, 48.954f, 91.099f, 48.955f)
                lineTo(90.877f, 48.996f)
                curveTo(89.427f, 49.396f, 87.897f, 49.404f, 86.442f, 49.02f)
                curveTo(84.988f, 48.636f, 83.662f, 47.873f, 82.599f, 46.809f)
                curveTo(81.536f, 45.745f, 80.774f, 44.418f, 80.392f, 42.963f)
                curveTo(80.009f, 41.509f, 80.019f, 39.979f, 80.42f, 38.529f)
                curveTo(80.44f, 38.471f, 80.45f, 38.409f, 80.451f, 38.347f)
                curveTo(80.46f, 38.216f, 80.438f, 38.085f, 80.385f, 37.966f)
                curveTo(80.332f, 37.846f, 80.25f, 37.741f, 80.147f, 37.66f)
                curveTo(80.045f, 37.579f, 79.923f, 37.524f, 79.794f, 37.501f)
                curveTo(79.665f, 37.478f, 79.533f, 37.487f, 79.408f, 37.527f)
                curveTo(77.706f, 38.004f, 76.159f, 38.918f, 74.92f, 40.179f)
                curveTo(73.682f, 41.439f, 72.795f, 43.002f, 72.347f, 44.712f)
                curveTo(71.9f, 46.422f, 71.908f, 48.219f, 72.371f, 49.925f)
                curveTo(72.834f, 51.63f, 73.735f, 53.185f, 74.985f, 54.434f)
                curveTo(76.235f, 55.683f, 77.791f, 56.583f, 79.497f, 57.045f)
                curveTo(81.203f, 57.506f, 83.0f, 57.513f, 84.71f, 57.064f)
                curveTo(86.419f, 56.616f, 87.981f, 55.727f, 89.241f, 54.488f)
                curveTo(90.5f, 53.248f, 91.413f, 51.7f, 91.889f, 49.998f)
                curveTo(91.94f, 49.847f, 91.94f, 49.683f, 91.889f, 49.532f)
                verticalLineTo(49.532f)
                close()
            }
        }
            .build()
        return _night!!
    }

private var _night: ImageVector? = null
