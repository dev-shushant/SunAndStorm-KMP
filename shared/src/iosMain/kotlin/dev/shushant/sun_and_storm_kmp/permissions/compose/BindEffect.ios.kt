package dev.shushant.sun_and_storm_kmp.permissions.compose

import androidx.compose.runtime.Composable
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController

// on iOS side we should not do anything to prepare PermissionsController to work
@Suppress("FunctionNaming","NO_ACTUAL_FOR_EXPECT")
@Composable
internal actual fun BindEffect(permissionsController: PermissionsController) = Unit
