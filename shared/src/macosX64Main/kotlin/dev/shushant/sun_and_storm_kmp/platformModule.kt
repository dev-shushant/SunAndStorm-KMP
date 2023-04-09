package dev.shushant.sun_and_storm_kmp

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual fun platformModule() = module {
    single<ObservableSettings> { NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults) }
}