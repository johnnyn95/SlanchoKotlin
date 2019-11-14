package com.example.slancho.repository.rapidApiOpenWeatherMap

interface RapidApiOpenWeatherMapRepository {

    suspend fun getCurrentForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getCurrentForecastByCityAndCountryCode(location: String)

    suspend fun getRapidApiThreeHourForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getRapidApiThreeHourForecastByCityAndCountryCode(location: String)

    suspend fun getRapidApiDailyForecastByLocation(latitude: Double, longitude: Double)

    suspend fun getRapidApiDailyForecastByCityAndCountryCode(location: String)
}