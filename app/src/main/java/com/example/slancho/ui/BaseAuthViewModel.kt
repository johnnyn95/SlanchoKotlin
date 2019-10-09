package com.example.slancho.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.slancho.db.model.User
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.utils.LocationManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

abstract class BaseAuthViewModel constructor(
    var locationManager: LocationManager,
    var firebaseAuth: FirebaseAuth,
    var userDbRepository: UserDbRepository
) : ViewModel(), FirebaseAuth.AuthStateListener {

    abstract val TAG: String

    val navigateToMain: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val navigateToSignIn: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    lateinit var currentUser: User

    abstract fun onScreenReady(context: Context, userId: String)

    override fun onAuthStateChanged(p0: FirebaseAuth) {
        if (firebaseAuth.currentUser == null) {
            navigateToSignIn()
        }
    }

    private fun navigateToMain(user: User) {
        navigateToMain.postValue(user.id)
    }


    fun navigateToSignIn() = navigateToSignIn.postValue(true)

    fun signOut(context: Context) {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.signOut()
        }

        if (GoogleSignIn.getLastSignedInAccount(context) != null) {
            GoogleSignIn.getClient(context, signInWithGoogle()).signOut()
                .addOnSuccessListener { navigateToSignIn() }
        }
    }

    fun signInWithGoogle(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
    }

    protected suspend fun fetchCurrentUser(context: Context) {
        val user = userDbRepository.getUserByAuthUID(fetchCurrentLoggedUserId(context))
        handleSignIn(user)
    }

    private fun fetchCurrentLoggedUserId(context: Context): String {
        var userId = ""
        if (firebaseAuth.currentUser != null) {
            userId = firebaseAuth.currentUser!!.uid
        }
        if (GoogleSignIn.getLastSignedInAccount(context) != null) {
            userId = GoogleSignIn.getLastSignedInAccount(context)!!.id!!
        }
        return userId
    }

    protected fun handleSignIn(user: User?) {
        if (user != null) {
            navigateToMain(user)
        } else {
            navigateToSignIn()
        }
    }

    fun handleSignUp(user: User?) {
        if (user != null) {
            navigateToMain(user)
        }
    }
}