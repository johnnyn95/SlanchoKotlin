package com.example.slancho.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    var locationManager: LocationManager,
    var firebaseAuth: FirebaseAuth
) : ViewModel(), FirebaseAuth.IdTokenListener {

    val onSignOutClicked = MutableLiveData<Boolean>()

    fun onScreenReady() {
        firebaseAuth.addIdTokenListener(this)
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    override fun onIdTokenChanged(p0: FirebaseAuth) {
        onSignOutClicked.postValue(true)
    }
}