import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.shushant.sun_and_storm_kmp.Platform
import dev.shushant.sun_and_storm_kmp.PlatformState
import dev.shushant.sun_and_storm_kmp.SunAndStormApp


@Composable
fun MainViewWeb() {
    PlatformState.value = Platform.WEB
    SunAndStormApp(showNavRail = false, modifier = Modifier)
}


