package com.example.slancho.repository.openWeatherMap

interface OpenWeatherMapRepository {
    suspend fun getThreeHourForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getThreeHourForecastByCityAndCountryCode(location: String)

    suspend fun getCurrentForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getCurrentForecastByCityAndCountryCode(location: String)
}