package com.example.slancho.ui.splash

import android.content.Context
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SplashActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository) {

    override val TAG: String get() = SplashActivityViewModel::class.java.simpleName

    override fun onScreenReady(context: Context, userId: String) {
        if (firebaseAuth.currentUser != null || GoogleSignIn.getLastSignedInAccount(context) != null) {
            runBlocking {
                fetchCurrentUser(context)
            }
        } else {
            navigateToSignIn()
        }
    }
}


