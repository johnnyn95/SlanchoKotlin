package com.example.slancho.repository.openWeatherMap

interface OpenWeatherMapRepository {
    suspend fun getThreeHourForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getThreeHourForecastByCityAndCountryCode(location: String)
}