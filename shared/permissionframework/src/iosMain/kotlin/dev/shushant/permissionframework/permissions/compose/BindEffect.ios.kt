package dev.shushant.permissionframework.permissions.compose

import androidx.compose.runtime.Composable
import dev.shushant.permissionframework.permissions.PermissionsController

// on iOS side we should not do anything to prepare PermissionsController to work
@Suppress("FunctionNaming","NO_ACTUAL_FOR_EXPECT")
@Composable
internal actual fun BindEffect(permissionsController: PermissionsController) = Unit
