package com.example.slancho.api.interceptors

import android.annotation.SuppressLint
import com.example.slancho.api.Environment
import com.example.slancho.api.TemperatureUnit
import com.example.slancho.utils.SharedPreferencesManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class OpenWeatherMapInterceptor : Interceptor {

    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    companion object {
        const val QUERY_PARAMETER_KEY = "appid"
        const val QUERY_PARAMETER_UNITS = "units"
        const val QUERY_PARAMETER_LANG = "lang"
    }

    fun setup(sharedPreferencesManager: SharedPreferencesManager): OpenWeatherMapInterceptor {
        this.sharedPreferencesManager = sharedPreferencesManager
        return this
    }

    @SuppressLint("TimberArgCount")
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url
        var newUrl = appendApiKey(httpUrl)
        if (sharedPreferencesManager.tempUnitValue.isNotEmpty() && sharedPreferencesManager.tempUnitValue != TemperatureUnit.Kelvin.value) {
            newUrl = appendTempUnit(newUrl)
        }
        if (sharedPreferencesManager.langValue.isNotEmpty()) {
            newUrl = appendLang(newUrl)
        }
        val requestBuilder = request.newBuilder().url(newUrl)
        request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun appendApiKey(url: HttpUrl): HttpUrl {
        return url.newBuilder().addQueryParameter(
            QUERY_PARAMETER_KEY,
            Environment.Environments.DEV.getOpenWeatherMapKey()
        ).build()
    }

    private fun appendTempUnit(url: HttpUrl): HttpUrl {
        return url.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_UNITS, sharedPreferencesManager.tempUnitValue)
            .build()
    }

    private fun appendLang(url: HttpUrl): HttpUrl {
        return url.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_LANG, sharedPreferencesManager.langValue)
            .build()
    }

}