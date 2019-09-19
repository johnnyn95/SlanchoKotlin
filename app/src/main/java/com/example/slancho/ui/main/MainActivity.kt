package com.example.slancho.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity.LEFT
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivityMainBinding
import com.example.slancho.ui.main.news.NewsFragment
import com.example.slancho.ui.main.search.SearchFragment
import com.example.slancho.ui.main.weather.WeatherFragment
import com.example.slancho.ui.signIn.SignInActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import dagger.android.AndroidInjection

class MainActivity : BaseActivity<ActivityMainBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var weatherFragment: WeatherFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var newsFragment: NewsFragment
    private lateinit var activeFragment: Fragment

    private var activeItemId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        checkForGrantedPermissions()
        viewModel.onScreenReady()
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initFields() {
        viewModel = getViewModel(MainActivityViewModel::class.java)
    }

    override fun initViews() {
        initToolbar()
        initBottomNavigation()
        initDrawer()
        initFragments()
    }

    override fun initListeners() {
        getBinding().viewNavigation.setNavigationItemSelectedListener {
            if (it.itemId == R.id.item_settings) {
                // TODO implement settings screen
            }
            if (it.itemId == R.id.item_sign_out) {
                viewModel.signOut()
            }
            closeDrawer()
            false
        }

        getBinding().grpBottomNav.setOnNavigationItemSelectedListener(this)

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
        searchFragment = SearchFragment.newInstance()
        newsFragment = NewsFragment.newInstance()

        val manager = supportFragmentManager
        for (fragment in listOf(
            weatherFragment,
            searchFragment,
            newsFragment
        )) {
            manager.beginTransaction().add(
                R.id.grp_fragment_container,
                fragment,
                fragment.javaClass.simpleName
            )
                .hide(fragment).commit()
        }
        activeFragment = weatherFragment
        manager.beginTransaction().show(weatherFragment).commit()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean =
        handleTabClick(menuItem.itemId)

    private fun handleTabClick(itemId: Int): Boolean {
        val replacement: Fragment? = when (activeItemId) {
            R.id.action_weather -> weatherFragment
            R.id.action_news -> newsFragment
            R.id.action_search -> searchFragment
            else -> return false
        }
        activeItemId = itemId
        replaceActiveFragment(itemId, replacement!!)
        return true
    }

    private fun replaceActiveFragment(itemId: Int, replacement: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(replacement).commit()
        activeFragment = replacement
        activeFragment.onResume()

        if (getBinding().grpBottomNav.selectedItemId != itemId) {
            getBinding().grpBottomNav.setOnNavigationItemSelectedListener(null)
            getBinding().grpBottomNav.selectedItemId = itemId
            getBinding().grpBottomNav.setOnNavigationItemSelectedListener(this)
        }
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

    private fun initBottomNavigation() {
        getBinding().grpBottomNav.labelVisibilityMode =
            LabelVisibilityMode.LABEL_VISIBILITY_SELECTED
    }

    @SuppressLint("RtlHardcoded")
    private fun closeDrawer() {
        if (getBinding().grpDrawer.isDrawerOpen(LEFT)) {
            getBinding().grpDrawer.closeDrawer(LEFT)
        }
    }
}
