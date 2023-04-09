package dev.shushant.sun_and_storm_kmp.location.utils

import android.content.Context
import android.location.LocationManager

object LocationUtil {
    fun checkLocationEnable(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
}