package com.example.slancho.repository.rapidApiOpenWeatherMap

interface RapidApiOpenWeatherMapRepository {
    suspend fun getRapidApiThreeHourForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getRapidApiThreeHourForecastByCityAndCountryCode(location: String)
}