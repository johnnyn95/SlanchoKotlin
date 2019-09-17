package com.example.slancho.ui.splash

import android.util.Log
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository) {

    override fun onScreenReady() {
        if (firebaseAuth.currentUser != null) {
            navigateToMain()
            Log.d(SplashActivityViewModel::class.java.simpleName, firebaseAuth.currentUser!!.uid)
        } else {
            navigateToSignIn()
        }
    }
}


