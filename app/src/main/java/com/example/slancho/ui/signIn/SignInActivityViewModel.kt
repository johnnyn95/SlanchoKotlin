package com.example.slancho.ui.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignInActivityViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    val navigateToMain: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val navigateToRegister: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun onScreenReady() {

    }

    fun navigateToMain() = navigateToMain.postValue(true)

    fun navigateToRegister() = navigateToRegister.postValue(true)


}


