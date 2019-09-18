package com.example.slancho.repository.user

import com.example.slancho.db.model.User

interface UserRepository {

    fun insertUser(authUID: String, isAnonymous: Boolean)

    fun getUserByAuthUID(authUID: String, callback: GetUserCallback)

    fun updateUserLastKnownLocation(user: User)

    interface GetUserCallback {
        fun onUserLoaded(user: User)
    }
}