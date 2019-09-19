package com.example.slancho.ui.signUp

import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignUpActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth
    , userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository) {

    override fun onScreenReady() {
    }

    suspend fun signUpWithEmailAndPassword(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser.uid, false)
    }
}


