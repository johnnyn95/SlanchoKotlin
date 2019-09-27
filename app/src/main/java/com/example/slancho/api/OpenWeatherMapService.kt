package com.example.slancho.api

import com.example.slancho.api.models.openWeatherMap.OpenWeatherMapForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {
    @GET("forecast")
    fun getForecastForXDaysByCityName(
        @Query("q") location: String,
        @Query("cnt") numberOfDays: Int
    ): Call<OpenWeatherMapForecastResponse>
}