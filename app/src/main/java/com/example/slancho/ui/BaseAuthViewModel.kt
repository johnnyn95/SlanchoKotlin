package com.example.slancho.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth

abstract class BaseAuthViewModel constructor(
    var locationManager: LocationManager,
    var firebaseAuth: FirebaseAuth,
    var userDbRepository: UserDbRepository
) : ViewModel(), FirebaseAuth.AuthStateListener {

    val onSignOutClicked = MutableLiveData<Boolean>()

    val navigateToMain: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val navigateToSignIn: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    abstract fun onScreenReady()

    override fun onAuthStateChanged(p0: FirebaseAuth) {
        if (firebaseAuth.currentUser == null) {
            onSignOutClicked.postValue(true)
        }
    }

    fun navigateToMain() = navigateToMain.postValue(true)

    fun navigateToSignIn() = navigateToSignIn.postValue(true)

    fun signOut() = firebaseAuth.signOut()

}