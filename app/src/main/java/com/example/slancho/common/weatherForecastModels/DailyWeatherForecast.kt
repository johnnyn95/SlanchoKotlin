package com.example.slancho.common.weatherForecastModels

import com.example.slancho.db.model.ForecastInfo
import org.joda.time.DateTime

class DailyWeatherForecast(
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
    val sunset: DateTime,
    val sunrise: DateTime,
    val tempMorning: Double,
    val tempEvening: Double,
    val tempNight: Double

) : WeatherForecast() {
    companion object {
        fun createFromForecastInfo(forecastInfo: ForecastInfo): DailyWeatherForecast {
            return DailyWeatherForecast(
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
                DateTime(forecastInfo.sunset),
                DateTime(forecastInfo.sunrise),
                forecastInfo.tempMorning ?: 0.0,
                forecastInfo.tempEvening ?: 0.0,
                forecastInfo.tempNight ?: 0.0
            )
        }
    }
}