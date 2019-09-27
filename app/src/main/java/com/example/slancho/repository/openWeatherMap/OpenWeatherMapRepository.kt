package com.example.slancho.repository.openWeatherMap

interface OpenWeatherMapRepository {
    suspend fun getRapidApiForecastWeatherData(location: String, latitude: Double, longitude: Double)

    suspend fun getForecastWeatherDataByLocation(latitude: Double, longitude: Double)

    suspend fun getForecastWeatherDataByCityAndCountryCode(location: String)
}