package com.example.slancho.ui.main

import android.content.Context
import androidx.work.WorkManager
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    locationManager: LocationManager,
    firebaseAuth: FirebaseAuth,
    userDbRepository: UserDbRepository,
    workManager: WorkManager
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository, workManager),
    FirebaseAuth.AuthStateListener {

    override fun onScreenReady(context: Context, userId: String) {
        registerAuthListeners()
        fetchCurrentUser(userId)
    }

    private fun registerAuthListeners() {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.addAuthStateListener(this)
        }
    }

    private fun fetchCurrentUser(userId: String) = CoroutineScope(IO).launch {
        launch {
            try {
                val user = userDbRepository.getUserById(userId)
                if (user != null) {
                    currentUser.postValue(user)
                    Timber.d("Fetched user ${user.authUID}")
                }
            } catch (e: NullPointerException) {
                Timber.e("Failed to fetch user $e")
                navigateToSignIn()
            }
        }
    }
}