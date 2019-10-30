package com.example.slancho.ui.signUp

import android.content.Context
import androidx.work.WorkManager
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignUpActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository,
    workManager: WorkManager
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository, workManager) {

    override fun onScreenReady(context: Context, userId: String) {
    }

    suspend fun signUpWithEmailAndPassword(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser.uid, false)
        val user = userDbRepository.getUserByAuthUID(firebaseUser.uid)
        handleSignUp(user)
    }
}


