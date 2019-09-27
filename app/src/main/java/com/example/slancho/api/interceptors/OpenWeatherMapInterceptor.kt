package com.example.slancho.api.interceptors

import android.util.Log
import com.example.slancho.api.Environment
import okhttp3.Interceptor
import okhttp3.Response

class OpenWeatherMapInterceptor : Interceptor {

    companion object {
        const val QUERY_PARAMETER_KEY = "appid"
        const val QUERY_PARAMETER_UNITS = "units"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url
        // TODO fetch units from preferences
        val newUrl = httpUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_KEY,
                Environment.Environments.DEV.getOpenWeatherMapKey())
            .addQueryParameter(QUERY_PARAMETER_UNITS,"metric")
            .build()
        Log.d("interceptor",newUrl.toString())
        val requestBuilder = request.newBuilder().url(newUrl)
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}