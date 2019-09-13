package com.example.slancho.ui.main

import androidx.lifecycle.ViewModel
import com.example.slancho.utils.LocationManager
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(var locationManager: LocationManager) : ViewModel() {
    fun onScreenReady() {
    }
}