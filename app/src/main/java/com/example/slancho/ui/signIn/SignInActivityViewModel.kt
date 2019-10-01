package com.example.slancho.ui.signIn

import android.content.Context
import android.content.Intent
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.BaseAuthViewModel
import com.example.slancho.utils.LocationManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import timber.log.Timber
import javax.inject.Inject


class SignInActivityViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth,
    locationManager: LocationManager,
    userDbRepository: UserDbRepository
) : BaseAuthViewModel(locationManager, firebaseAuth, userDbRepository) {

    override val TAG: String get() = SignInActivityViewModel::class.java.simpleName

    override fun onScreenReady(context: Context) {
    }

    suspend fun signInAnonymously(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser.uid, true)
    }

    suspend fun signInWithEmailAndPassword(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser.uid, false)
    }

    private suspend fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        userDbRepository.insertUser(googleSignInAccount.id!!, false)
    }

    suspend fun handleGoogleSignInResult(data: Intent?) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val googleSignInAccount = task.getResult(ApiException::class.java)
            signInWithGoogle(googleSignInAccount!!)
            navigateToMain()
        } catch (e: ApiException) {
            Timber.w(
                SignInActivityViewModel::class.java.simpleName,
                "Failed Google Sign in {${e.statusCode}}"
            )
        }
    }

}


