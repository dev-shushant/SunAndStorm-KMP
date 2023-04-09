package dev.shushant.sun_and_storm_kmp.di

import dev.shushant.sun_and_storm_kmp.network.WeatherApi
import dev.shushant.sun_and_storm_kmp.network.WeatherApiImpl
import dev.shushant.sun_and_storm_kmp.platformModule
import dev.shushant.sun_and_storm_kmp.pref.AppSettings
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.observer.ResponseObserver
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule())
    }

// called by iOS etc
fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule() = module {
    single { createJson() }
    single { createHttpClient() }

    single<WeatherApi> { WeatherApiImpl(client = get()) }

    single { AppSettings(get()) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient() =
    HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

