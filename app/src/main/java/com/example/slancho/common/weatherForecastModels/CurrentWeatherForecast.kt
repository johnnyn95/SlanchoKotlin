package com.example.slancho.common.weatherForecastModels

import com.example.slancho.api.ForecastType
import com.example.slancho.db.model.ForecastInfo
import com.example.slancho.utils.WeatherFormatUtils
import org.joda.time.DateTime

class CurrentWeatherForecast(
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
    val sunrise: DateTime
) : WeatherForecast() {
    override fun isCollapsable(): Boolean = false
    override fun getForecastType(): ForecastType = ForecastType.Current

    companion object {
        private fun createFromForecastInfo(forecastInfo: ForecastInfo): CurrentWeatherForecast {
            return CurrentWeatherForecast(
                forecastInfo.id,
                forecastInfo.forecastId,
                WeatherFormatUtils.initJodaDateTime(forecastInfo.dateTime!!),
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
                WeatherFormatUtils.initJodaDateTime(forecastInfo.sunset!!),
                WeatherFormatUtils.initJodaDateTime(forecastInfo.sunrise!!)
            )
        }

        fun createFromForecastInfoList(list: List<ForecastInfo>): List<CurrentWeatherForecast> {
            val listItems = ArrayList<CurrentWeatherForecast>()
            list.listIterator().forEach { forecastInfo ->
                listItems.add(createFromForecastInfo(forecastInfo))
            }
            return listItems
        }
    }
}