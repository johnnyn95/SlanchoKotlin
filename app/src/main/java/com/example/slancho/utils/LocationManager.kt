package com.example.slancho.utils

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class LocationManager @Inject constructor(var application: Application) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastKnownLocation: Location

    init {
        initFusedLocationClient()
    }

    private fun initFusedLocationClient() {
        GlobalScope.launch {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
            getLastKnownLocation()
        }
    }

    fun getLastKnownLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                lastKnownLocation = location
                return@addOnSuccessListener
            }
        }

    }

    fun getAddressFromLastKnownLocation(): Address {
        getLastKnownLocation()
        val geoCoder = Geocoder(application, Locale.getDefault())
        val addresses =
            geoCoder.getFromLocation(lastKnownLocation.latitude, lastKnownLocation.longitude, 1)
        return addresses[0]
    }
}