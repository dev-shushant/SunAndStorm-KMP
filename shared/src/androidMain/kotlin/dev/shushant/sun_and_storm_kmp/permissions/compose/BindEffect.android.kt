package dev.shushant.sun_and_storm_kmp.permissions.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import dev.shushant.sun_and_storm_kmp.permissions.PermissionsController

@Suppress("FunctionNaming")
@Composable
internal actual fun BindEffect(permissionsController: PermissionsController) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context: Context = LocalContext.current

    LaunchedEffect(permissionsController, lifecycleOwner, context) {
        /*val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager
        permissionsController.onDataRecieved(lifecycleOwner.lifecycle, fragmentManager)
   */
    }
}
