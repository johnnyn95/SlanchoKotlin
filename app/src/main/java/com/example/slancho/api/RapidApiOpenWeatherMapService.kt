package com.example.slancho.api

import com.example.slancho.api.models.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapCurrentForecastResponse
import com.example.slancho.api.models.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapDailyForecastResponse
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

    @GET("forecast/daily")
    fun getDailyForecastWeatherData(
        @Query("q") location: String?,
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?,
        @Query("cnt") numberOfDays: Int
    ): Call<RapidApiOpenWeatherMapDailyForecastResponse>

    @GET("weather")
    fun getCurrentForecast(
        @Query("q") location: String?,
        @Query("lat") latitude: Double?,
        @Query("lon") longitude: Double?
    ): Call<RapidApiOpenWeatherMapCurrentForecastResponse>
}