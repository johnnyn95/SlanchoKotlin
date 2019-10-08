package com.example.slancho.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.slancho.api.models.openWeatherMap.OpenWeatherMapForecastResponse
import java.util.*

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "dateTimeOfFetch")
    val dateTimeOfFetch: Long,
    @ColumnInfo(name = "cod")
    val cod: String,
    @ColumnInfo(name = "message")
    val message: Double,
    @ColumnInfo(name = "fetchedForNumberOfDays")
    val fetchedForNumberOfDays: Int,
    @ColumnInfo(name = "cityName")
    val cityName: String,
    @ColumnInfo(name = "cityId")
    val cityId: Long
) {
    @Ignore
    lateinit var city: City

    constructor(openWeatherMapForecastResponse: OpenWeatherMapForecastResponse) : this(
        UUID.randomUUID().toString(),
        System.currentTimeMillis(),
        openWeatherMapForecastResponse.cod!!,
        openWeatherMapForecastResponse.message!!,
        openWeatherMapForecastResponse.numberOfDays!!,
        openWeatherMapForecastResponse.city!!.name!!,
        openWeatherMapForecastResponse.city!!.cityId!!
    ) {
        this.city = City(openWeatherMapForecastResponse.city!!)
    }
}