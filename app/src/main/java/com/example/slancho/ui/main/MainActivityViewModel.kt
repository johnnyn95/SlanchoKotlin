package com.example.slancho.ui.main

import android.content.Context
import com.example.slancho.db.model.User
import com.example.slancho.repository.forecast.ForecastDbRepository
import com.example.slancho.repository.openWeatherMap.OpenWeatherMapApiRepository
import com.example.slancho.repository.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapApiRepository
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository,
    private var openWeatherMapApiRepository: OpenWeatherMapApiRepository,
    private var rapidApiOpenWeatherMapRepository: RapidApiOpenWeatherMapApiRepository,
    private var forecastDbRepository: ForecastDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository),
    FirebaseAuth.AuthStateListener {

    override val TAG: String get() = MainActivityViewModel::class.java.simpleName

    override fun onScreenReady(context: Context, userId: String) {
        registerAuthListeners()
        fetchCurrentUser(userId)
    }

    private fun registerAuthListeners() {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.addAuthStateListener(this)
        }
    }

    private fun fetchCurrentUser(userId: String) = runBlocking(IO) {
        launch {
            try {
                val user = userDbRepository.getUserById(userId)
                if (user != null) {
                    currentUser = user
                    fetchForecastData(currentUser)
                    Timber.d("Fetched user ${currentUser.authUID}")
                }
            } catch (e: NullPointerException) {
                Timber.e("Failed to fetch user $e")
                navigateToSignIn()
            }
        }
    }

    private suspend fun fetchForecastData(user: User) {
        openWeatherMapApiRepository.getThreeHourForecastByLocation(
            user.lastKnownLocation!!.latitude,
            user.lastKnownLocation!!.longitude
        )

        rapidApiOpenWeatherMapRepository.getRapidApiDailyForecastByCityAndCountryCode(user.lastKnownLocation!!.getFormattedCityAndCountryCode())

        val forecast =
            forecastDbRepository.getLatestForecastByCityName(user.lastKnownLocation!!.city)
    }
}