package com.example.slancho.ui.splash

import android.content.Context
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository) {

    override fun onScreenReady(context: Context) {
        if (firebaseAuth.currentUser != null || GoogleSignIn.getLastSignedInAccount(context) != null) {
            navigateToMain()
        } else {
            navigateToSignIn()
        }
    }
}


