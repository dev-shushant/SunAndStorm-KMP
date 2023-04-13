package dev.shushant.permissionframework.permissions

class RequestCanceledException(
    val permission: Permission,
    message: String? = null
) : Exception(message)
