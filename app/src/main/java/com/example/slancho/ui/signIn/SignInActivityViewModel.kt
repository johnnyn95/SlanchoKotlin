package com.example.slancho.ui.signIn

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignInActivityViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    fun onScreenReady() {

    }
}


