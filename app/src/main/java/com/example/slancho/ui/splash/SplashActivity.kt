package com.example.slancho.ui.splash

import android.os.Bundle
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivitySplashBinding
import dagger.android.AndroidInjection

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        // TODO Navigate to Login screen
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initFields() {}

    override fun initViews() {
        viewModel = getViewModel(SplashActivityViewModel::class.java)
    }

    override fun initListeners() {}

}
