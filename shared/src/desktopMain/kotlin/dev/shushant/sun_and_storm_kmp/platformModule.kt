package dev.shushant.sun_and_storm_kmp

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import org.koin.dsl.module
import java.util.prefs.Preferences

actual fun platformModule() = module {
    single<ObservableSettings> { PreferencesSettings(Preferences.userRoot()) }
}
