package com.example.slancho.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(firebaseAuth: FirebaseAuth) : ViewModel() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    val navigateToMain: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val navigateToLogin: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun onScreenReady() {
        if (firebaseAuth.currentUser != null) {
            navigateToMain()
        } else {
            navigateToLogin()
        }
    }

    fun navigateToMain() = navigateToMain.postValue(true)

    fun navigateToLogin() = navigateToLogin.postValue(true)
}


