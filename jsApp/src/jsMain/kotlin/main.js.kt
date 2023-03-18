import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import moe.tlaster.precompose.preComposeWindow
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        preComposeWindow("SunAndStorm App") {
            val innerWidth = window.innerWidth
            Column(
                modifier = Modifier.fillMaxHeight().width(innerWidth.dp)
            ) {
                MainViewWeb()
            }
        }
    }
}

