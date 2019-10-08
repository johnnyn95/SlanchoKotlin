package com.example.slancho.ui.main

import android.content.Context
import com.example.slancho.repository.forecast.ForecastDbRepository
import com.example.slancho.repository.openWeatherMap.OpenWeatherMapApiRepository
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository,
    private var openWeatherMapApiRepository: OpenWeatherMapApiRepository,
    private var forecastDbRepository: ForecastDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository),
    FirebaseAuth.AuthStateListener {

    override val TAG: String get() = MainActivityViewModel::class.java.simpleName

    override fun onScreenReady(context: Context) {
        registerAuthListeners()
        runBlocking(IO) {
            try {
                fetchCurrentUser(context)
                if (currentUser != null) {
                    Timber.d(TAG, "Fetched user${currentUser!!.authUID}")
                    openWeatherMapApiRepository.getForecastWeatherDataByLocation(
                        currentUser!!.lastKnownLocation!!.latitude,
                        currentUser!!.lastKnownLocation!!.longitude
                    )
                    val forecast =
                        forecastDbRepository.getLatestForecastByCityName(currentUser!!.lastKnownLocation!!.city)
                }
            } catch (e: NullPointerException) {
                Timber.w(TAG, "Failed to fetch user$e")
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