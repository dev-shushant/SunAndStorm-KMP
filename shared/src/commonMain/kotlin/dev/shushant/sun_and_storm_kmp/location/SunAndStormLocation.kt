package dev.shushant.sun_and_storm_kmp.location

import dev.shushant.sun_and_storm_kmp.location.extension.appending
import dev.shushant.sun_and_storm_kmp.location.extension.removed
import dev.shushant.sun_and_storm_kmp.location.native.NativeAtomicReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.native.concurrent.ThreadLocal

class SunAndStormLocation {

    @ThreadLocal
    companion object : SunAndStormLocationCompanion {

        // -------------------------------------------------------------------------------------------
        //  Implementation of SunAndStormLocationCompanion
        // -------------------------------------------------------------------------------------------

        override fun currentLocation(block: OnLocationUpdatedBlock) {
            onLocationUpdatedBlocks.value = onLocationUpdatedBlocks.value.appending(block)
            locationManager.getCurrentLocation()
        }

        override fun isPermissionAllowed() = locationManager.isPermissionAllowed()

        override fun requestPermission() = locationManager.requestPermission()

        override suspend fun startLocationUpdating() = withContext(Dispatchers.Default){
            locationManager.startLocationUpdating()
        }


        override fun stopLocationUpdating() = locationManager.stopLocationUpdating()

        override fun onLocationUnavailable(
            target: Any,
            block: OnLocationUnavailableBlock
        ): SunAndStormLocationCompanion {
            onLocationUnavailableBlockMap.value = onLocationUnavailableBlockMap.value.appending(target, block)
            return this
        }

        override fun onLocationUpdated(
            target: Any,
            block: OnLocationUpdatedBlock
        ): SunAndStormLocationCompanion {
            onLocationUpdatedBlockMap.value = onLocationUpdatedBlockMap.value.appending(target, block)
            return this
        }

        override fun onPermissionUpdated(
            target: Any,
            block: OnPermissionUpdatedBlock
        ): SunAndStormLocationCompanion {
            onPermissionUpdatedBlockMap.value = onPermissionUpdatedBlockMap.value.appending(target, block)
            return this
        }

        override fun removeAllListeners() {
            onLocationUnavailableBlockMap.value = emptyMap()
            onLocationUpdatedBlockMap.value = emptyMap()
            onPermissionUpdatedBlockMap.value = emptyMap()
            onLocationUpdatedBlocks.value = emptyList()
            locationManager.removeAllListeners()
        }

        override fun removeListeners(target: Any) {
            removeOnPermissionUpdated(target)
            removeOnLocationUnavailable(target)
            removeOnLocationUpdated(target)
            locationManager.removeListeners(target)
        }

        override fun removeOnLocationUnavailable(target: Any) {
            onLocationUnavailableBlockMap.value = onLocationUnavailableBlockMap.value.removed(target)
        }

        override fun removeOnLocationUpdated(target: Any) {
            onLocationUpdatedBlockMap.value = onLocationUpdatedBlockMap.value.removed(target)
        }

        override fun removeOnPermissionUpdated(target: Any) {
            onPermissionUpdatedBlockMap.value = onPermissionUpdatedBlockMap.value.removed(target)
        }

        // -------------------------------------------------------------------------------------------
        //  Internal
        // -------------------------------------------------------------------------------------------

        internal val locationManager: LocationManager by lazy { LocationManager() }

        internal fun notifyOnLocationUpdated(data: LocationData) {
            onLocationUpdatedBlockMap.value.forEach { it.value(data) }
            onLocationUpdatedBlocks.value.forEach { it(data) }
            onLocationUpdatedBlocks.value = emptyList()
        }

        internal fun notifyOnLocationUnavailable() {
            onLocationUnavailableBlockMap.value.forEach { it.value() }
        }
        internal fun notifyOnPermissionUpdated(isGranted: Boolean) {
            onPermissionUpdatedBlockMap.value.forEach { it.value(isGranted) }
        }

        // -------------------------------------------------------------------------------------------
        //  Private
        // -------------------------------------------------------------------------------------------

        private val onLocationUpdatedBlocks = NativeAtomicReference(listOf<OnLocationUpdatedBlock>())
        private val onLocationUpdatedBlockMap = NativeAtomicReference(mapOf<Any, OnLocationUpdatedBlock>())
        private val onLocationUnavailableBlockMap = NativeAtomicReference(mapOf<Any, OnLocationUnavailableBlock>())
        private val onPermissionUpdatedBlockMap = NativeAtomicReference(mapOf<Any, OnPermissionUpdatedBlock>())
    }
}