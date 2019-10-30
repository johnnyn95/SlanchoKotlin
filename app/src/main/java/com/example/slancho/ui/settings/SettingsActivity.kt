package com.example.slancho.ui.settings

import android.view.MenuItem
import com.example.slancho.R
import com.example.slancho.common.BaseActivity
import com.example.slancho.databinding.SettingsActivityBinding

class SettingsActivity : BaseActivity<SettingsActivityBinding>() {

    override fun initFields() {
    }

    override fun initViews() {
        initToolbar()
        initSettingsFragment()
    }

    override fun initListeners() {
    }

    override fun getLayoutResId(): Int = R.layout.settings_activity

    private fun initSettingsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }

    private fun initToolbar() {
        setSupportActionBar(getBinding().toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow_primary_color)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}