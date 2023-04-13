package dev.shushant.permissionframework.permissions.compose

import androidx.compose.runtime.Composable
import dev.shushant.permissionframework.permissions.PermissionsController

@Suppress("FunctionNaming","NO_ACTUAL_FOR_EXPECT")
@Composable
internal expect fun BindEffect(permissionsController: PermissionsController)
