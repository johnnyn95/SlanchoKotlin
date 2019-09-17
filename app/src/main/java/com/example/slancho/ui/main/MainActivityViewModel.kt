package com.example.slancho.ui.main

import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth
    , userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository),
    FirebaseAuth.AuthStateListener {

    override fun onScreenReady() {
        firebaseAuth.addAuthStateListener(this)
    }


}