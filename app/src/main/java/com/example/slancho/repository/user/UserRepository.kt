package com.example.slancho.repository.user

import com.example.slancho.db.model.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    fun insertUser(firebaseUser: FirebaseUser, isAnonymous: Boolean)

    fun getUserByAuthUID(authUID: String, callback: GetUserCallback)

    interface GetUserCallback {
        fun onUserLoaded(user: User)
    }
}