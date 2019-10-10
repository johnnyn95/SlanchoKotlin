package com.example.slancho.api

import com.example.slancho.api.models.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapTheeHourForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RapidApiOpenWeatherMapService {
    @GET("forecast")
    fun getThreeHourForecastWeatherData(
        @Query("q") location: String?,
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("cnt") numberOfPeriods: Int
    ): Call<RapidApiOpenWeatherMapTheeHourForecastResponse>
}