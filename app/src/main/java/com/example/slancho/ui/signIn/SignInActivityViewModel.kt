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

    override fun onScreenReady(context: Context, userId: String) {
    }

    suspend fun signInWithFirebase(firebaseUser: FirebaseUser) {
        userDbRepository.insertUser(firebaseUser.uid, true)
        handleSignIn(userDbRepository.getUserByAuthUID(firebaseUser.uid))
    }

    private suspend fun signInWithGoogle(googleSignInAccount: GoogleSignInAccount) {
        userDbRepository.insertUser(googleSignInAccount.id!!, false)
        handleSignIn(userDbRepository.getUserByAuthUID(googleSignInAccount.id!!))
    }

    suspend fun handleGoogleSignInResult(data: Intent?) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val googleSignInAccount = task.getResult(ApiException::class.java)
            signInWithGoogle(googleSignInAccount!!)
        } catch (e: ApiException) {
            Timber.w("Failed Google Sign in {${e.statusCode}}")
        }
    }

}


