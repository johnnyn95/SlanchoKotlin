package com.example.slancho.repository.user

import com.example.slancho.db.dao.LastKnownLocationDao
import com.example.slancho.db.dao.UserDao
import com.example.slancho.db.model.LastKnownLocation
import com.example.slancho.db.model.User
import com.example.slancho.utils.LocationManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

class UserDbRepository @Inject constructor(
    var locationManager: LocationManager,
    var userDao: UserDao,
    var lastKnownLocationDao: LastKnownLocationDao
) :
    UserRepository {

    companion object {
        val TAG = UserDbRepository::class.java.simpleName
    }

    override suspend fun insertUser(authUID: String, isAnonymous: Boolean) {
        runBlocking(IO) {
            val user = User(authUID = authUID, isAnonymous = isAnonymous)
            val address = locationManager.getAddressFromLastKnownLocation()
            if (address != null) {
                val lastKnownLocation = LastKnownLocation(userId = user.id, address = address)
                lastKnownLocationDao.insert(lastKnownLocation)
                user.lastKnownLocation = lastKnownLocation
            }
            userDao.insert(user)
        }
    }

    override suspend fun getUserByAuthUID(authUID: String): User? {
        return runBlocking(IO) {
            val user: User? = userDao.getUserByAuthUID(authUID)
            val lastKnownLocation: LastKnownLocation?
            if (user != null) {
                // Tries to fetch the current location
                val address = locationManager.getAddressFromLastKnownLocation()
                if (address != null) {
                    lastKnownLocation = LastKnownLocation(userId = user.id, address = address)
                    lastKnownLocationDao.insert(lastKnownLocation)
                    user.lastKnownLocation = lastKnownLocation
                } else {
                    // Uses the last known location
                    lastKnownLocation =
                        lastKnownLocationDao.getLastKnownLocationForUserByUserId(userId = user.id)
                    if (lastKnownLocation != null) {
                        user.lastKnownLocation = lastKnownLocation
                    }
                }
                user
            } else {
                Timber.w(TAG, "Couldn't fetch User")
                null
            }
        }
    }

    override suspend fun getUserById(id: String): User? {
        return runBlocking(IO) {
            val user: User? = userDao.getUserById(id)
            val lastKnownLocation: LastKnownLocation?
            if (user != null) {
                // Tries to fetch the current location
                val address = locationManager.getAddressFromLastKnownLocation()
                if (address != null) {
                    lastKnownLocation = LastKnownLocation(userId = user.id, address = address)
                    lastKnownLocationDao.insert(lastKnownLocation)
                    user.lastKnownLocation = lastKnownLocation
                } else {
                    // Uses the last known location
                    lastKnownLocation =
                        lastKnownLocationDao.getLastKnownLocationForUserByUserId(userId = user.id)
                    if (lastKnownLocation != null) {
                        user.lastKnownLocation = lastKnownLocation
                    }
                }
                user
            } else {
                Timber.w(TAG, "Couldn't fetch User")
                null
            }
        }
    }
}
