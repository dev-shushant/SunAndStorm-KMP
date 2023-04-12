package dev.shushant.sun_and_storm_kmp.permissions.compose

import androidx.compose.runtime.Composable
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController

@Suppress("FunctionNaming","NO_ACTUAL_FOR_EXPECT")
@Composable
internal expect fun BindEffect(permissionsController: PermissionsController)
