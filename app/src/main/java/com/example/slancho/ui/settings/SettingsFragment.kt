package com.example.slancho.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.slancho.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
    // TODO Restart the app whenever a crucial setting is change f.e. Temperature Unit or Location
}