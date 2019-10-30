package com.example.slancho.ui.main.search

import androidx.work.WorkManager
import com.example.slancho.db.model.User
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.main.BaseMainFragmentViewModel
import com.example.slancho.utils.LocationManager
import javax.inject.Inject

class SearchFragmentViewModel @Inject constructor(
     locationManager: LocationManager,
     userDbRepository: UserDbRepository,
     workManager: WorkManager

) : BaseMainFragmentViewModel(locationManager, userDbRepository, workManager) {

    override val TAG: String = SearchFragmentViewModel::javaClass.name

    override fun onScreenReady(user: User) {
    }

}