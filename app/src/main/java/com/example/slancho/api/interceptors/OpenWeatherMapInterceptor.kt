package com.example.slancho.api.interceptors

import com.example.slancho.api.TemperatureUnit
import com.example.slancho.utils.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class OpenWeatherMapInterceptor : BaseWeatherInterceptor() {

    override fun setup(sharedPreferencesManager: SharedPreferencesManager): OpenWeatherMapInterceptor {
        this.sharedPreferencesManager = sharedPreferencesManager
        return this
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var newUrl = appendOpenWeatherMapApiKey(request.url)
        if (sharedPreferencesManager.tempUnitValue != TemperatureUnit.Kelvin.value) {
            newUrl = appendTempUnit(newUrl)
        }
        newUrl = appendLang(newUrl)
        val requestBuilder = request.newBuilder().url(newUrl)
        request = requestBuilder.build()
        return chain.proceed(request)
    }

}