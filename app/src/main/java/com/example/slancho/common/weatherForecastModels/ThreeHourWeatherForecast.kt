package com.example.slancho.common.weatherForecastModels

import com.example.slancho.db.model.ForecastInfo
import org.joda.time.DateTime

class ThreeHourWeatherForecast(
    override val id: String,
    override val forecastId: String,
    override val dateTime: DateTime,
    override val icon: String,
    override val info: String,
    override val description: String,
    override val temp: Double,
    override val tempMin: Double,
    override val tempMax: Double,
    override val pressure: Double,
    override val windSpeed: Double,
    override val windDegrees: Double,
    override val humidityPercentage: Double,
    override val cloudsPercentage: Double,
    val seaLevel: Double,
    val groundLevel: Double
) : WeatherForecast() {
    companion object {
        fun createFromForecastInfo(forecastInfo: ForecastInfo): ThreeHourWeatherForecast {
            return ThreeHourWeatherForecast(
                forecastInfo.id,
                forecastInfo.forecastId,
                DateTime(forecastInfo.dateTime),
                forecastInfo.weatherIcon ?: "",
                forecastInfo.weatherInfo ?: "",
                forecastInfo.weatherDescription ?: "",
                forecastInfo.temp ?: 0.0,
                forecastInfo.tempMin ?: 0.0,
                forecastInfo.tempMax ?: 0.0,
                forecastInfo.pressure ?: 0.0,
                forecastInfo.windSpeed ?: 0.0,
                forecastInfo.windDegrees ?: 0.0,
                forecastInfo.humidityPercentage ?: 0.0,
                forecastInfo.cloudsPercentage ?: 0.0,
                forecastInfo.seaLevel ?: 0.0,
                forecastInfo.groundLevel ?: 0.0
            )
        }
    }
}