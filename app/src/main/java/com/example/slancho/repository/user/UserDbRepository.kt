package com.example.slancho.repository.user

import com.example.slancho.db.dao.LastKnownLocationDao
import com.example.slancho.db.dao.UserDao
import com.example.slancho.db.model.LastKnownLocation
import com.example.slancho.db.model.User
import com.example.slancho.utils.LocationManager
import javax.inject.Inject

class UserDbRepository @Inject constructor(
    var locationManager: LocationManager,
    var userDao: UserDao,
    var lastKnownLocationDao: LastKnownLocationDao
) :
    UserRepository {
    override suspend fun insertUser(authUID: String, isAnonymous: Boolean) {
        val user = User(authUID = authUID, isAnonymous = isAnonymous)
        val address = locationManager.getAddressFromLastKnownLocation()
        val lastKnownLocation = LastKnownLocation(userId = user.id, address = address)
        lastKnownLocationDao.insert(lastKnownLocation)
        userDao.insert(user)
    }

    override suspend fun getUserByAuthUID(authUID: String): User {
        val user = userDao.getUserByAuthUID(authUID)
        val address = locationManager.getAddressFromLastKnownLocation()
        val lastKnownLocation = LastKnownLocation(userId = user.id, address = address)
        lastKnownLocationDao.insert(lastKnownLocation)
        user.lastKnownLocation = lastKnownLocation
        return user
    }
}
