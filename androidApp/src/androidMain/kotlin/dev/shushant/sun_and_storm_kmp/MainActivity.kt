package dev.shushant.sun_and_storm_kmp

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation
import dev.shushant.sun_and_storm_kmp.location.extension.processRequestPermissionsResult
import dev.shushant.sun_and_storm_kmp.view.AppViewAndroid
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            AppViewAndroid()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        SunAndStormLocation.processRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
