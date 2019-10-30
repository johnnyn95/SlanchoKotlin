package com.example.slancho.ui.main

import androidx.lifecycle.ViewModel
import androidx.work.WorkManager
import com.example.slancho.db.model.User
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.utils.LocationManager

abstract class BaseMainFragmentViewModel constructor(
    val locationManager: LocationManager,
    val userDbRepository: UserDbRepository,
    val workManager: WorkManager
) : ViewModel() {
    abstract val TAG: String

    abstract fun onScreenReady(user: User)
}