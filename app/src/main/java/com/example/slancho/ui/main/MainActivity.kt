package com.example.slancho.ui.main

import android.location.Location
import android.os.Bundle
import android.util.Log
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivityMainBinding
import com.example.slancho.ui.main.weather.WeatherFragment
import com.example.slancho.utils.LocationManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var weatherFragment: WeatherFragment

    @Inject
    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        checkForGrantedPermissions()
//        initFragments()
        locationManager.getLastKnownLocation(object :
            LocationManager.OnLocationReceivedListener {
            override fun onLocationReceived(location: Location) {
                Log.d(
                    MainActivity::class.java.simpleName,
                    locationManager.getAddressFromLastKnownLocation(location)
                )
            }
        })

    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initFields() {
        viewModel = getViewModel(MainActivityViewModel::class.java)
    }

    override fun initViews() {
    }

    override fun initListeners() {}

    private fun initFragments() {
        weatherFragment = WeatherFragment.newInstance()
    }

}
