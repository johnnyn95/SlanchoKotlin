package com.example.slancho.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.slancho.api.models.openWeatherMap.ThreeHourWeatherResponse
import com.example.slancho.api.models.rapidApiOpenWeatherMap.DailyWeatherResponse
import java.util.*

@Entity(tableName = "forecastInfo")
data class ForecastInfo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @NonNull
    @ColumnInfo(name = "forecastId")
    val forecastId: String,
    @ColumnInfo(name = "dateTime")
    val dateTime: Long?,
    @ColumnInfo(name = "temp")
    val temp: Double?,
    @ColumnInfo(name = "tempMin")
    val tempMin: Double?,
    @ColumnInfo(name = "tempMax")
    val tempMax: Double?,
    @ColumnInfo(name = "tempNight")
    val tempNight: Double?,
    @ColumnInfo(name = "tempEvening")
    val tempEvening: Double?,
    @ColumnInfo(name = "tempMorning")
    val tempMorning: Double?,
    @ColumnInfo(name = "sunrise")
    val sunrise: Long?,
    @ColumnInfo(name = "sunset")
    val sunset: Long?,
    @ColumnInfo(name = "pressure")
    val pressure: Double?,
    @ColumnInfo(name = "seaLevel")
    val seaLevel: Double?,
    @ColumnInfo(name = "groundLevel")
    val groundLevel: Double?,
    @ColumnInfo(name = "humidityPercentage")
    val humidityPercentage: Double?,
    @ColumnInfo(name = "cloudsPercentage")
    val cloudsPercentage: Double?,
    @ColumnInfo(name = "windSpeed")
    val windSpeed: Double?,
    @ColumnInfo(name = "windDegrees")
    val windDegrees: Double?,
    @ColumnInfo(name = "dateTimeText")
    val dateTimeText: String?,
    @ColumnInfo(name = "weatherInfoId")
    val weatherInfoId: Long?,
    @ColumnInfo(name = "weatherInfo")
    val weatherInfo: String?,
    @ColumnInfo(name = "weatherDescription")
    val weatherDescription: String?,
    @ColumnInfo(name = "weatherIcon")
    val weatherIcon: String?
) {

    /**
     * Used for creation of Daily Forecast data object
     */
    constructor(
        theeHourWeatherResponse: ThreeHourWeatherResponse,
        forecastId: String
    ) : this(
        UUID.randomUUID().toString(),
        forecastId,
        theeHourWeatherResponse.dateTime,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.temp,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.tempMin,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.tempMax,
        null, null, null,
        null, null,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.pressure,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.seaLevel,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.grndLevel,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.humidity,
        theeHourWeatherResponse.cloudsResponse!!.all,
        theeHourWeatherResponse.windResponse!!.speed,
        theeHourWeatherResponse.windResponse!!.deg,
        theeHourWeatherResponse.dateTimeTxt,
        theeHourWeatherResponse.weatherInfo!!.first().id,
        theeHourWeatherResponse.weatherInfo!!.first().main,
        theeHourWeatherResponse.weatherInfo!!.first().description,
        theeHourWeatherResponse.weatherInfo!!.first().icon
    )

    /**
     * Used for creation of Daily Forecast data object from RapidApi
     */
    constructor(
        theeHourWeatherResponse: com.example.slancho.api.models.rapidApiOpenWeatherMap.ThreeHourWeatherResponse,
        forecastId: String
    ) : this(
        UUID.randomUUID().toString(),
        forecastId,
        theeHourWeatherResponse.dateTime,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.temp,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.tempMin,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.tempMax,
        null, null, null,
        null, null,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.pressure,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.seaLevel,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.grndLevel,
        theeHourWeatherResponse.threeHourWeatherMainResponse!!.humidity,
        theeHourWeatherResponse.cloudsResponse!!.all,
        theeHourWeatherResponse.windResponse!!.speed,
        theeHourWeatherResponse.windResponse!!.deg,
        theeHourWeatherResponse.dateTimeTxt,
        theeHourWeatherResponse.weatherInfo!!.first().id,
        theeHourWeatherResponse.weatherInfo!!.first().main,
        theeHourWeatherResponse.weatherInfo!!.first().description,
        theeHourWeatherResponse.weatherInfo!!.first().icon
    )

    /**
     * Used for creation of Three Hour Forecast data object
     */
    constructor(
        dailyWeatherResponse: DailyWeatherResponse,
        forecastId: String
    ) : this(
        UUID.randomUUID().toString(),
        forecastId,
        dailyWeatherResponse.dateTime,
        dailyWeatherResponse.temperatureResponse.day,
        dailyWeatherResponse.temperatureResponse.min,
        dailyWeatherResponse.temperatureResponse.max,
        dailyWeatherResponse.temperatureResponse.night,
        dailyWeatherResponse.temperatureResponse.eve,
        dailyWeatherResponse.temperatureResponse.morn,
        dailyWeatherResponse.sunrise,
        dailyWeatherResponse.sunset,
        dailyWeatherResponse.pressure,
        null, null,
        dailyWeatherResponse.humidity,
        dailyWeatherResponse.cloud,
        dailyWeatherResponse.speed,
        dailyWeatherResponse.deg,
        null,
        dailyWeatherResponse.weatherInfo!!.first().id,
        dailyWeatherResponse.weatherInfo!!.first().main,
        dailyWeatherResponse.weatherInfo!!.first().description,
        dailyWeatherResponse.weatherInfo!!.first().icon
    )
}