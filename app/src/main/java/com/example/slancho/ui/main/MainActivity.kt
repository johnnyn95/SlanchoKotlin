package com.example.slancho.ui.main

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivityMainBinding
import com.example.slancho.ui.main.weather.WeatherFragment
import com.example.slancho.ui.signIn.SignInActivity
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
        initToolbar()
        initDrawer()
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
        viewModel.onScreenReady()
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initFields() {
        viewModel = getViewModel(MainActivityViewModel::class.java)
    }

    override fun initViews() {
    }

    override fun initListeners() {
        getBinding().viewNavigation.setNavigationItemSelectedListener {
            if (it.itemId == R.id.item_settings) {
            }
            if (it.itemId == R.id.item_sign_out) {
                viewModel.signOut()
            }
            false
        }

        viewModel.onSignOutClicked.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, SignInActivity::class.java))
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        hideKeyboard()
        if (item.itemId == android.R.id.home) {
            getBinding().grpDrawer.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initFragments() {
        weatherFragment = WeatherFragment.newInstance()
    }

    private fun initToolbar() {
        setSupportActionBar(getBinding().toolbar)
        if (supportActionBar != null) {
            supportActionBar?.title = ""
            supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }

    private fun initDrawer() {
        val headerView = getBinding().viewNavigation.getHeaderView(0)
        if (headerView != null) {
            headerView.findViewById<TextView>(R.id.txt_release_version).text = getAppVersion()
        }
        getBinding().grpDrawer.setScrimColor(getColor(R.color.colorPrimary))
    }

}
