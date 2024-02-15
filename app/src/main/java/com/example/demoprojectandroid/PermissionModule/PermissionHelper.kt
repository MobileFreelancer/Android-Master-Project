package com.example.demoprojectandroid.PermissionModule

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionHelper(private val target: Activity, private val requestCode: Int, private val permissions: Array<String>) {



    init {
        if (target !is Activity) {
            throw IllegalArgumentException("Target must be an Activity")
        }
    }

    fun requestPermissions() {
        if (hasPermissions()) {
            onPermissionsGranted()
        } else {
            ActivityCompat.requestPermissions(target, permissions, requestCode)
        }
    }

    private fun hasPermissions(): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(target, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun onRequestPermissionsResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == this.requestCode) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                onPermissionsGranted()
            } else {
                onPermissionsDenied()
            }
        }
    }

    private fun onPermissionsGranted() {
        // Handle permissions granted
    }

    private fun onPermissionsDenied() {
        // Handle permissions denied
    }
}
