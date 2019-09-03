package com.example.slancho.ui.login

import android.os.Bundle
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivityLoginBinding
import dagger.android.AndroidInjection

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun initFields() {
    }

    override fun initViews() {
        viewModel = getViewModel(LoginActivityViewModel::class.java)
    }

    override fun initListeners() {
    }

    override fun getLayoutResId(): Int = R.layout.activity_login

}
