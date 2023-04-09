package dev.shushant.sun_and_storm_kmp.location.utils

import android.content.Context
import androidx.startup.Initializer
import dev.shushant.sun_and_storm_kmp.location.extension.configure
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation


@Suppress("UNUSED")
internal class ModuleInitializer : Initializer<Int> {
    override fun create(context: Context): Int {
        SunAndStormLocation.configure(context)
        return 0
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}