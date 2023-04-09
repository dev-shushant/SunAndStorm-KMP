package shushant.sun_and_storm_kmp

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.shushant.sun_and_storm_kmp.AppViewDesktop
import dev.shushant.sun_and_storm_kmp.di.initKoin
import moe.tlaster.precompose.PreComposeWindow
import java.awt.Dimension
import java.awt.Toolkit
import java.net.InetAddress

private val koin = initKoin(enableNetworkLogs = true).koin

fun main() = application {
    println(InetAddress.getLocalHost())
    val configuration = Toolkit.getDefaultToolkit().screenSize
    PreComposeWindow(
        onCloseRequest = ::exitApplication,
        title = "SunAndStorm-KMP",
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = getPreferredWindowSize(1000, 1000)
        ),
    ) {
        AppViewDesktop(
            deviceConfiguration = configuration
        )
    }
}

fun getPreferredWindowSize(desiredWidth: Int, desiredHeight: Int): DpSize {
    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val preferredWidth: Int = (screenSize.width * 0.8f).toInt()
    val preferredHeight: Int = (screenSize.height * 0.8f).toInt()
    val width: Int = if (desiredWidth < preferredWidth) desiredWidth else preferredWidth
    val height: Int = if (desiredHeight < preferredHeight) desiredHeight else preferredHeight
    return DpSize(width.dp, height.dp)
}

