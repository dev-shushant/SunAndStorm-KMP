package dev.shushant.sun_and_storm_kmp.permissions

class RequestCanceledException(
    val permission: Permission,
    message: String? = null
) : Exception(message)
