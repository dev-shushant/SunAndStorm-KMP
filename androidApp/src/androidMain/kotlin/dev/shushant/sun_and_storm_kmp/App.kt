package dev.shushant.sun_and_storm_kmp

import android.app.Application
import dev.shushant.sun_and_storm_kmp.di.initKoin
import org.koin.core.component.KoinComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@App)
        }
    }
}