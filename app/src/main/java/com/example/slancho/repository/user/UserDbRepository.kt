package com.example.slancho.repository.user

import com.example.slancho.AppExecutors
import com.example.slancho.db.dao.UserDao
import com.example.slancho.db.model.User
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserDbRepository @Inject constructor(var appExecutors: AppExecutors, var userDao: UserDao) :
    UserRepository {
    override fun insertUser(firebaseUser: FirebaseUser, isAnonymous: Boolean) {
        appExecutors.diskIO().execute {
            userDao.insert(User(authUID = firebaseUser.uid, isAnonymous = isAnonymous))
        }
    }

    override fun getUserByAuthUID(authUID: String, callback: UserRepository.GetUserCallback) {
        appExecutors.diskIO().execute {
            val user = userDao.getUserByAuthUID(authUID)
            appExecutors.mainThread().execute { callback.onUserLoaded(user) }
        }
    }

}