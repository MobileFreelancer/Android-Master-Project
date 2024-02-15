package com.example.demoprojectandroid.Helper

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

public class LocationHelper(context: Context) {

    private val MIN_TIME_BW_UPDATES: Long = 1000 // 1 second
    private val MIN_DISTANCE_CHANGE_FOR_UPDATES = 1.0f // 1 meter

    private val context: Context = context
    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val latitude: Double = location.latitude
            val longitude: Double = location.longitude
            saveLocationInPreferences(latitude, longitude)

        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    private fun saveLocationInPreferences(latitude: Double, longitude: Double) {
        Log.e("TAG", "saveLocationInPreferences: Saved in preference $latitude $longitude")
    }

    fun requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
                                              ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        // Start location updates only when explicitly requested
        locationManager.requestLocationUpdates(
            LocationManager.FUSED_PROVIDER,
            MIN_TIME_BW_UPDATES,
            MIN_DISTANCE_CHANGE_FOR_UPDATES,
            locationListener)
    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }
}