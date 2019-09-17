package com.example.slancho.ui.signIn

import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignInActivityViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth,
    locationManager: LocationManager,
    userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository) {

    override fun onScreenReady() {
    }

    fun signInAnonymously(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser, true)
    }

    fun signInWithEmailAndPassword(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser, false)
    }
}


