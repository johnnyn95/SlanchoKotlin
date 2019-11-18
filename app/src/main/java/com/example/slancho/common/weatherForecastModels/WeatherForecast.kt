package com.example.slancho.common.weatherForecastModels

import com.example.slancho.api.ForecastType
import org.joda.time.DateTime

abstract class WeatherForecast : AdapterCard() {
    abstract val id: String
    abstract val forecastId: String
    abstract val dateTime: DateTime
    abstract val icon: String
    abstract val info: String
    abstract val description: String
    abstract val temp: Double
    abstract val tempMin: Double
    abstract val tempMax: Double
    abstract val pressure: Double
    abstract val windSpeed: Double
    abstract val windDegrees: Double
    abstract val humidityPercentage: Double
    abstract val cloudsPercentage: Double
    override fun getCardType(): AdapterCardType = AdapterCardType.WeatherCard
    abstract fun getForecastType(): ForecastType
    abstract override fun isCollapsable(): Boolean
}