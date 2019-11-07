package com.example.slancho.repository.forecast

import com.example.slancho.api.ForecastType
import com.example.slancho.db.model.Forecast

interface ForecastRepository {
    suspend fun insertForecast(forecast: Forecast)

    suspend fun getLatestForecastByCityNameAndForecastType(
        cityName: String,
        forecastType: ForecastType
    ): Forecast

    suspend fun getLatestForecastByLatLongAndForecastType(
        lat: Double,
        long: Double,
        forecastType: ForecastType
    ): Forecast
}