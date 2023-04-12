package dev.shushant.models

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform