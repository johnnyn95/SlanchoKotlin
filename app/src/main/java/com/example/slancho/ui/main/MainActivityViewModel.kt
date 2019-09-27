package com.example.slancho.ui.main

import com.example.slancho.db.model.User
import com.example.slancho.repository.openWeatherMap.OpenWeatherMapApiRepository
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository,
    var openWeatherMapApiRepository: OpenWeatherMapApiRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository),
    FirebaseAuth.AuthStateListener {

    private var currentUser: User? = null

    override fun onScreenReady() {
        firebaseAuth.addAuthStateListener(this)
        CoroutineScope(IO).launch {
            currentUser = fetchCurrentUser()
            if (currentUser != null) {
//                openWeatherMapApiRepository.getForecastWeatherData(
//                    currentUser!!.lastKnownLocation.getFormattedLocation(),
//                    currentUser!!.lastKnownLocation.latitude,
//                    currentUser!!.lastKnownLocation.longitude
//                )
                openWeatherMapApiRepository.getForecastForXDaysByCityName(
                    currentUser!!.lastKnownLocation.getFormattedCityAndCountryCode(),
                    16
                )
            }
        }
    }

    private suspend fun fetchCurrentUser(): User? {
        return userDbRepository.getUserByAuthUID(firebaseAuth.currentUser!!.uid)
    }
}