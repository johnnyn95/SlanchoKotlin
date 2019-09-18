package com.example.slancho.ui.main

import com.example.slancho.db.model.User
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.repository.user.UserRepository
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

    private lateinit var currentUser: User

    override fun onScreenReady() {
        firebaseAuth.addAuthStateListener(this)
        fetchCurrentUser()
    }

    fun fetchCurrentUser() {
        userDbRepository.getUserByAuthUID(firebaseAuth.currentUser!!.uid,
            object : UserRepository.GetUserCallback {
                override fun onUserLoaded(user: User) {
                    currentUser = user
                }
            })
    }

}