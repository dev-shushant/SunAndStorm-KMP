package dev.shushant.sun_and_storm_kmp

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module

actual fun platformModule() = module {
    single { createObservableSettings(get()) }
}


private fun createObservableSettings(context: Context): ObservableSettings {
    return SharedPreferencesSettings(
        context.getSharedPreferences(
            "AppSettings",
            Context.MODE_PRIVATE
        )
    )
}
