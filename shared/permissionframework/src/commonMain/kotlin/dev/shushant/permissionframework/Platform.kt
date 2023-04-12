package dev.shushant.permissionframework

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform