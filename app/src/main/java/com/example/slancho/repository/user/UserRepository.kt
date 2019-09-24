package com.example.slancho.repository.user

import com.example.slancho.db.model.User

interface UserRepository {
    suspend fun insertUser(authUID: String, isAnonymous: Boolean)

    suspend fun getUserByAuthUID(authUID: String) : User?
}