package com.example.slancho.repository.user

import android.location.Location
import com.example.slancho.AppExecutors
import com.example.slancho.db.dao.LastKnownLocationDao
import com.example.slancho.db.dao.UserDao
import com.example.slancho.db.model.LastKnownLocation
import com.example.slancho.db.model.User
import com.example.slancho.utils.LocationManager
import javax.inject.Inject

class UserDbRepository @Inject constructor(
    var appExecutors: AppExecutors,
    var locationManager: LocationManager,
    var userDao: UserDao,
    var lastKnownLocationDao: LastKnownLocationDao
) :
    UserRepository {
    override fun insertUser(authUID: String, isAnonymous: Boolean) {
        appExecutors.diskIO().execute {
            val user = User(authUID = authUID, isAnonymous = isAnonymous)
            locationManager.getLastKnownLocation(object :
                LocationManager.OnLocationReceivedListener {
                override fun onLocationReceived(location: Location) {
                    val address = locationManager.getAddressFromLastKnownLocation(location)
                    lastKnownLocationDao.insert(
                        LastKnownLocation(user.id, address)
                    )
                }
            })
            userDao.insert(user)
        }
    }

    override fun getUserByAuthUID(authUID: String, callback: UserRepository.GetUserCallback) {
        appExecutors.diskIO().execute {
            val user = userDao.getUserByAuthUID(authUID)
            appExecutors.mainThread().execute { callback.onUserLoaded(user) }
//
//            locationManager.getLastKnownLocation(object :
//                LocationManager.OnLocationReceivedListener {
//                override fun onLocationReceived(location: Location) {
//                    lastKnownLocationDao.insert(
//                        LastKnownLocation(
//                            user.id,
//                            locationManager.getAddressFromLastKnownLocation(location)
//                        )
//                    )
//                    user.lastKnownLocation =
//                        lastKnownLocationDao.getLastKnownLocationForUserByUserId(user.id)
//                    appExecutors.mainThread().execute { callback.onUserLoaded(user) }
//                }
//            })
        }
    }

    override fun updateUserLastKnownLocation(user: User) {
        appExecutors.diskIO().execute {
            locationManager.getLastKnownLocation(object :
                LocationManager.OnLocationReceivedListener {
                override fun onLocationReceived(location: Location) {
                    lastKnownLocationDao.insert(
                        LastKnownLocation(
                            user.id,
                            locationManager.getAddressFromLastKnownLocation(location)
                        )
                    )
                }
            })
        }
    }
}