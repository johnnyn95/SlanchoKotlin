package com.example.slancho.utils

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermissionsManager @Inject constructor(var application: Application) {
    companion object {
        val permissions = listOf(
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION
        )

        const val permissionsRequestCode = 12345
    }

    fun initRequestPermissionsProcess(activity: Activity) {
        var i = 0
        while (i < permissions.size) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i]) != PERMISSION_GRANTED) {
                askForPermission(activity, permissions[i])
            } else {
                i++
            }
        }
    }

    private fun askForPermission(activity: Activity, permission: String) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(permission),
            permissionsRequestCode
        )
    }

    fun checkForGrantedPermissions(activity: Activity): Boolean {
        val permissionsGranted = true
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PERMISSION_GRANTED) {
                return false
            }
        }
        return permissionsGranted
    }

}