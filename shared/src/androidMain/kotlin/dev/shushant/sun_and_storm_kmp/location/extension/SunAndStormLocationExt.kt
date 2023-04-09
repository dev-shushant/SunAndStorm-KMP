package dev.shushant.sun_and_storm_kmp.location.extension

import android.app.Activity
import android.content.Context
import android.location.Location
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocation
import dev.shushant.sun_and_storm_kmp.location.SunAndStormLocationRequest
import dev.shushant.sun_and_storm_kmp.location.Address
import dev.shushant.sun_and_storm_kmp.location.Coordinates
import dev.shushant.sun_and_storm_kmp.location.LocationData
import java.lang.ref.WeakReference

fun SunAndStormLocation.Companion.processRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String?>,
    grantResults: IntArray
) = locationManager.processRequestPermissionsResult(
    requestCode,
    permissions,
    grantResults
)

fun SunAndStormLocation.Companion.showNotificationSetting() =
    locationManager.showNotificationSetting()

fun SunAndStormLocation.Companion.setLocationRequest(locationRequest: SunAndStormLocationRequest) =
    locationManager.setLocationRequest(locationRequest)


internal var SunAndStormLocation.Companion.activity: Activity?
    get() = locationManager.activity?.get()
    set(value) {
        locationManager.activity = WeakReference(value)
    }

internal fun SunAndStormLocation.Companion.configure(context: Context) =
    locationManager.configure(context)

fun Location.toLocationData(address: Address = Address()): LocationData = LocationData(
    accuracy.toDouble(),
    altitude,
    0.0,
    bearing.toDouble(),
    speed.toDouble(),
    Coordinates(latitude, longitude),
    address = address
)