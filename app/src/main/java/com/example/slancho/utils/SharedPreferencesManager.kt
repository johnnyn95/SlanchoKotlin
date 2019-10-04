package com.example.slancho.utils

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.slancho.R
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(var application: Application) {
    companion object {
        const val fetchDataDefaultValue = false
        const val forecastDataForDaysDefaultValue = 7
    }

    private lateinit var locationKey: String
    lateinit var locationValue: String

    private lateinit var tempUnitKey: String
    lateinit var tempUnitValue: String

    private lateinit var langKey: String
    lateinit var langValue: String

    private lateinit var fetchDataKey: String
    private var fetchDataValue: Boolean = fetchDataDefaultValue

    private lateinit var forecastDataForDaysKey: String
    var forecastDataForDaysValue: Int = forecastDataForDaysDefaultValue

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application.baseContext)

    init {
        initSharedPreferenceKeys()
        fetchSharedPreferences()
    }

    private fun initSharedPreferenceKeys() {
        locationKey = application.applicationContext.getString(R.string.location_key)
        tempUnitKey =
            application.applicationContext.getString(R.string.temperature_unit_key)
        langKey = application.applicationContext.getString(R.string.language_key)
        fetchDataKey = application.applicationContext.getString(R.string.fetch_data_key)
        forecastDataForDaysKey =
            application.applicationContext.getString(R.string.forecast_data_for_days_key)
    }

    private fun fetchSharedPreferences() {
        locationValue = sharedPreferences.getString(locationKey, "")!!
        tempUnitValue = sharedPreferences.getString(tempUnitKey, "")!!
        langValue = sharedPreferences.getString(langKey, "")!!
        fetchDataValue = sharedPreferences.getBoolean(fetchDataKey, fetchDataDefaultValue)
        forecastDataForDaysValue =
            sharedPreferences.getInt(forecastDataForDaysKey, forecastDataForDaysDefaultValue)
    }
}