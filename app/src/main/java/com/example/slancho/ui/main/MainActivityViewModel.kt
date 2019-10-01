package com.example.slancho.ui.main

import android.content.Context
import android.util.Log
import com.example.slancho.repository.openWeatherMap.OpenWeatherMapApiRepository
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository,
    private var openWeatherMapApiRepository: OpenWeatherMapApiRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository),
    FirebaseAuth.AuthStateListener {

    override fun onScreenReady(context: Context) {
        registerAuthListeners()
        runBlocking(IO) {
            try {
                fetchCurrentUser(context)
                openWeatherMapApiRepository.getRapidApiForecastWeatherData(
                    currentUser!!.lastKnownLocation.getFormattedLocation(),
                    currentUser!!.lastKnownLocation.latitude,
                    currentUser!!.lastKnownLocation.longitude
                )
                openWeatherMapApiRepository.getForecastWeatherDataByLocation(
                    currentUser!!.lastKnownLocation.latitude,
                    currentUser!!.lastKnownLocation.longitude
                )
                openWeatherMapApiRepository.getForecastWeatherDataByCityAndCountryCode(
                    currentUser!!.lastKnownLocation.getFormattedCityAndCountryCode()
                )

            } catch (e: NullPointerException) {
                Log.w(MainActivityViewModel::class.java.simpleName, "Failed to fetch user$e")
                navigateToSignIn()
            }
        }
    }

    private fun registerAuthListeners() {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.addAuthStateListener(this)
        }
    }
}