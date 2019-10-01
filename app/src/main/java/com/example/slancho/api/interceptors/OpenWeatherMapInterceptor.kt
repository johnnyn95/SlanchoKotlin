package com.example.slancho.api.interceptors

import android.annotation.SuppressLint
import com.example.slancho.api.Environment
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class OpenWeatherMapInterceptor : Interceptor {

    companion object {
        const val QUERY_PARAMETER_KEY = "appid"
        const val QUERY_PARAMETER_UNITS = "units"
    }

    @SuppressLint("TimberArgCount")
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url
        // TODO fetch units from preferences
        val newUrl = httpUrl.newBuilder()
            .addQueryParameter(
                QUERY_PARAMETER_KEY,
                Environment.Environments.DEV.getOpenWeatherMapKey()
            )
            .addQueryParameter(QUERY_PARAMETER_UNITS, "metric")
            .build()
        Timber.d("interceptor", newUrl.toString())
        val requestBuilder = request.newBuilder().url(newUrl)
        request = requestBuilder.build()
        return chain.proceed(request)
    }
}