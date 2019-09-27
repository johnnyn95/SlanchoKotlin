package com.example.slancho.api

import com.example.slancho.api.models.openWeatherMap.OpenWeatherMapForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {
    @GET("forecast")
    fun getForecastForNumberOfDays(
        @Query("q") location: String?,
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("cnt") numberOfDays: Int
    ): Call<OpenWeatherMapForecastResponse>
}