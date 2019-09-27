package com.example.slancho.api.interceptors

import com.example.slancho.api.Environment
import okhttp3.Interceptor
import okhttp3.Response

class RapidApiOpenWeatherMapInterceptor : Interceptor {

    companion object {
        const val HEADER = "x-rapidapi-key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.header(HEADER) == null) {
            val newRequest = request.newBuilder()
                .addHeader(HEADER, Environment.Environments.DEV.getRapidApiOpenWeatherMapKey()).build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }

}