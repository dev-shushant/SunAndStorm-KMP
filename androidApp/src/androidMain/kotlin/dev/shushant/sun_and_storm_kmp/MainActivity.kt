package dev.shushant.sun_and_storm_kmp

import android.os.Bundle
import androidx.core.view.WindowCompat
import dev.shushant.sun_and_storm_kmp.view.AppViewAndroid
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            AppViewAndroid()
        }
    }
}
