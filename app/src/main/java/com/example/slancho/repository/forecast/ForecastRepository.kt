package com.example.slancho.repository.forecast

import com.example.slancho.db.model.Forecast

interface ForecastRepository {
    suspend fun insertForecast(forecast: Forecast)

    suspend fun getLatestForecastByCityName(cityName: String): Forecast

    suspend fun getLatestForecastByLatLong(lat: Double, long: Double): Forecast

}