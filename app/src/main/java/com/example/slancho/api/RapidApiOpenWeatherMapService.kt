package com.example.slancho.api

import com.example.slancho.api.models.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RapidApiOpenWeatherMapService {
    @GET("forecast")
    fun getForecastWeatherData(
        @Query("q") location: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Call<RapidApiOpenWeatherMapForecastResponse>
}