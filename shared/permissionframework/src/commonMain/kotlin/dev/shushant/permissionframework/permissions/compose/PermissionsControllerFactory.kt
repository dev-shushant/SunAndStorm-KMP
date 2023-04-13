/*
 * Copyright 2023 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.shushant.permissionframework.permissions.compose

import androidx.compose.runtime.Composable
import dev.shushant.permissionframework.permissions.PermissionsController

fun interface PermissionsControllerFactory {
    fun createPermissionsController(): PermissionsController
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
@Composable
internal expect fun rememberPermissionsControllerFactory(): PermissionsControllerFactory
