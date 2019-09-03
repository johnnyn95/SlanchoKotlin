package com.example.slancho.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivitySplashBinding
import com.example.slancho.ui.login.LoginActivity
import com.example.slancho.ui.main.MainActivity
import dagger.android.AndroidInjection

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    lateinit var viewModel: SplashActivityViewModel
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initNavigationSubscribers()
        postDelayedOnScreenReady()
    }

    private fun initNavigationSubscribers() {
        viewModel.navigateToMain.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java))
        })
        viewModel.navigateToLogin.observe(this, Observer {
            startActivity(Intent(this, LoginActivity::class.java))
        })
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initFields() {
        viewModel = getViewModel(SplashActivityViewModel::class.java)
        handler = Handler()

    }

    override fun initViews() {
    }

    override fun initListeners() {}

    private fun postDelayedOnScreenReady() {
        runnable = Runnable { viewModel.onScreenReady() }
        handler.postDelayed(runnable, 1000)
    }
}
