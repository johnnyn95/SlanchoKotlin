package com.example.slancho.api.interceptors

import com.example.slancho.api.Environment
import okhttp3.Interceptor
import okhttp3.Response

class OpenWeatherMapInterceptor : Interceptor {
    companion object {
        const val HEADER = "x-rapidapi-key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        //        val httpUrl = request.url.newBuilder().addQueryParameter()
        val request = chain.request()
        request.newBuilder().addHeader(HEADER, Environment.Environments.DEV.getOpenWeatherMapKey())
        return chain.proceed(request)
    }
}