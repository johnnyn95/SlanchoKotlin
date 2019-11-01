package com.example.slancho.api.interceptors

import com.example.slancho.utils.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class PexelsInterceptor : BaseWeatherInterceptor() {

    override fun setup(sharedPreferencesManager: SharedPreferencesManager): PexelsInterceptor {
        this.sharedPreferencesManager = sharedPreferencesManager
        return this
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = appendPexelsAuthHeader(request)
        val requestBuilder = request.newBuilder().url(request.url)
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}