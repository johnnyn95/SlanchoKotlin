package com.example.slancho.utils

import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*
import javax.inject.Inject

class LocationManager @Inject constructor(var application: Application) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastKnownLocation: Location

    interface OnLocationReceivedListener {
        fun onLocationReceived(location: Location)
    }

    init {
        initFusedLocationClient()
    }

    private fun initFusedLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
        getLastKnownLocation(object : OnLocationReceivedListener {
            override fun onLocationReceived(location: Location) {
                lastKnownLocation = location
            }
        })
    }

    fun getLastKnownLocation(listener: OnLocationReceivedListener) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                listener.onLocationReceived(location)
            }
        }
    }

    fun getAddressFromLastKnownLocation(location: Location): String {
        val geoCoder = Geocoder(application, Locale.getDefault())
        val addresses =
            geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        val address = addresses[0]
        return "${address.locality},${address.countryName}"
    }
}