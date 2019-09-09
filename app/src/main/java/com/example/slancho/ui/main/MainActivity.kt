package com.example.slancho.ui.main

import android.os.Bundle
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.ActivityMainBinding
import dagger.android.AndroidInjection

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
//        initFragments()
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initFields() { viewModel = getViewModel(MainActivityViewModel::class.java) }

    override fun initViews() {}

    override fun initListeners() {}

    private fun initFragments() { mainFragment = MainFragment.newInstance() }
}
