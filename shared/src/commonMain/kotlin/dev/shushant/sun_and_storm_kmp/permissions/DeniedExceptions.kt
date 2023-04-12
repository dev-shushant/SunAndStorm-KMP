package dev.shushant.sun_and_storm_kmp.permissions

open class DeniedException(
    val permission: Permission,
    message: String? = null
) : Exception(message)

class DeniedAlwaysException(
    permission: Permission,
    message: String? = null
) : DeniedException(permission, message)
