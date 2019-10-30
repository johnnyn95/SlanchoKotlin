package com.example.slancho.api.interceptors

import com.example.slancho.api.Environment
import com.example.slancho.utils.SharedPreferencesManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

open class BaseWeatherInterceptor : Interceptor {

    protected lateinit var sharedPreferencesManager: SharedPreferencesManager

    companion object {
        const val QUERY_PARAMETER_KEY = "appid"
        const val QUERY_PARAMETER_UNITS = "units"
        const val QUERY_PARAMETER_LANG = "lang"
        const val HEADER = "x-rapidapi-key"
    }

    open fun setup(sharedPreferencesManager: SharedPreferencesManager): Any {
        this.sharedPreferencesManager = sharedPreferencesManager
        return this
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }

    protected fun appendOpenWeatherMapApiKey(url: HttpUrl): HttpUrl {
        return url.newBuilder().addQueryParameter(
            QUERY_PARAMETER_KEY,
            Environment.Environments.DEV.getOpenWeatherMapKey()
        ).build()
    }

    protected fun appendTempUnit(url: HttpUrl): HttpUrl {
        return url.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_UNITS, sharedPreferencesManager.tempUnitValue)
            .build()
    }

    protected fun appendLang(url: HttpUrl): HttpUrl {
        return url.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_LANG, sharedPreferencesManager.langValue)
            .build()
    }

    protected fun appendRapidApiAuthHeader(request: Request): Request {
        return if (request.header(HEADER) == null) {
            request.newBuilder()
                .addHeader(HEADER, Environment.Environments.DEV.getRapidApiOpenWeatherMapKey())
                .build()
        } else request
    }
}