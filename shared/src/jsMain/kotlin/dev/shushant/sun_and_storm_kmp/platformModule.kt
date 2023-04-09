package dev.shushant.sun_and_storm_kmp

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import org.koin.dsl.module

@OptIn(ExperimentalSettingsApi::class)
actual fun platformModule() = module {
    single { StorageSettings().toSuspendSettings() }
}