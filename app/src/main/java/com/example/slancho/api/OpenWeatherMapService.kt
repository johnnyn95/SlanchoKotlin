package com.example.slancho.api

import com.example.slancho.api.models.openWeatherMap.OpenWeatherMapThreeHourForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {
    @GET("forecast")
    fun getThreeHourForecastForNumberOfPeriods(
        @Query("q") location: String?,
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("cnt") numberOfPeriods: Int
    ): Call<OpenWeatherMapThreeHourForecastResponse>
}