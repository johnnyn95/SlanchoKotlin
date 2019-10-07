package com.example.slancho.utils

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.util.*
import javax.inject.Inject

class LocationManager @Inject constructor(var application: Application) {

    companion object {
        val TAG = LocationManager::class.java.simpleName
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lastKnownLocation: Location? = null

    init {
        initFusedLocationClient()
    }

    private fun initFusedLocationClient() {
        GlobalScope.launch {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
            getLastKnownLocation()
        }
    }

    private suspend fun getLastKnownLocation() {
        withContext(IO) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    lastKnownLocation = location
                    return@addOnSuccessListener
                }
                Timber.w(TAG, "Couldn't fetch Location")
            }
        }
    }

    suspend fun getAddressFromLastKnownLocation(): Address? {
        return withContext(IO) {
            getLastKnownLocation()
            val geoCoder = Geocoder(application, Locale.getDefault())
            try {
                val addresses =
                    geoCoder.getFromLocation(
                        lastKnownLocation!!.latitude,
                        lastKnownLocation!!.longitude,
                        1
                    )
                if (addresses.size > 0) {
                    addresses[0]
                } else {
                    Timber.w(TAG, "No address found for this Location")
                    null
                }
            } catch (e: IOException) {
                Timber.w(TAG, "Couldn't fetch Address from Location")
                null
            }
        }
    }
}
