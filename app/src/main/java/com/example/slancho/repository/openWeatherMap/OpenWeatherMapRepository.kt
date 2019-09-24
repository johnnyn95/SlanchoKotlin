package com.example.slancho.repository.openWeatherMap

interface OpenWeatherMapRepository {
    suspend fun getForecastWeatherData(location: String, latitude: Double, longitude: Double)
}