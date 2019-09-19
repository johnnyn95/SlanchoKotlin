package com.example.slancho.ui.main

import com.example.slancho.db.model.User
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth
    , userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository),
    FirebaseAuth.AuthStateListener {

    private lateinit var currentUser: User

    override fun onScreenReady() {
        firebaseAuth.addAuthStateListener(this)
        GlobalScope.launch(Dispatchers.IO) {
            currentUser = fetchCurrentUser()
        }
    }

    private suspend fun fetchCurrentUser(): User {
        return userDbRepository.getUserByAuthUID(firebaseAuth.currentUser!!.uid)
    }
}