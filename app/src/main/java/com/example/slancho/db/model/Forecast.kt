package com.example.slancho.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.slancho.api.ForecastType
import com.example.slancho.api.models.openWeatherMap.OpenWeatherMapThreeHourForecastResponse
import com.example.slancho.api.models.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapDailyForecastResponse
import com.example.slancho.api.models.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapTheeHourForecastResponse
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
    val cityId: Long,
    @ColumnInfo(name = "forecastType")
    val forecastType: String
) {
    companion object {
        // Existing City Id +1 in order not to "randomly" create existing city
        const val RANDOM_SEED: Long = 7270111
    }

    @Ignore
    var city: City? = null

    /**
     * Used for the Three hour forecast by the OpenWeather Api
     */
    constructor(
        openWeatherMapThreeHourForecastResponse: OpenWeatherMapThreeHourForecastResponse,
        forecastType: ForecastType
    ) : this(
        UUID.randomUUID().toString(),
        System.currentTimeMillis(),
        openWeatherMapThreeHourForecastResponse.cod!!,
        openWeatherMapThreeHourForecastResponse.message!!,
        if (openWeatherMapThreeHourForecastResponse.numberOfPeriods != null) openWeatherMapThreeHourForecastResponse.numberOfPeriods!! else 0,
        openWeatherMapThreeHourForecastResponse.city!!.name!!,
        if (openWeatherMapThreeHourForecastResponse.city!!.cityId != null)
            openWeatherMapThreeHourForecastResponse.city!!.cityId!!
        else Random(RANDOM_SEED).nextLong(),
        forecastType.value
    ) {
        this.city = City(openWeatherMapThreeHourForecastResponse.city!!, this.cityId)
    }

    /**
     * Used for the Three hour forecast by the RapidApiOpenWeather Api
     */
    constructor(
        rapidApiThreeHourForecastResponse: RapidApiOpenWeatherMapTheeHourForecastResponse,
        forecastType: ForecastType
    ) : this(
        UUID.randomUUID().toString(),
        System.currentTimeMillis(),
        rapidApiThreeHourForecastResponse.cod!!,
        rapidApiThreeHourForecastResponse.message!!,
        if (rapidApiThreeHourForecastResponse.numberOfPeriods != null) rapidApiThreeHourForecastResponse.numberOfPeriods!! else 0,
        rapidApiThreeHourForecastResponse.city!!.name!!,
        if (rapidApiThreeHourForecastResponse.city!!.cityId != null)
            rapidApiThreeHourForecastResponse.city!!.cityId!!
        else Random(RANDOM_SEED).nextLong(),
        forecastType.value
    ) {
        this.city = City(rapidApiThreeHourForecastResponse.city!!, this.cityId)
    }

    /**
     * Used for the Daily forecast by the RapidApiOpenWeather Api
     */
    constructor(
        rapidApiOpenWeatherMapDailyForecastResponse: RapidApiOpenWeatherMapDailyForecastResponse,
        forecastType: ForecastType
    ) : this(
        UUID.randomUUID().toString(),
        System.currentTimeMillis(),
        rapidApiOpenWeatherMapDailyForecastResponse.cod!!,
        rapidApiOpenWeatherMapDailyForecastResponse.message!!,
        if (rapidApiOpenWeatherMapDailyForecastResponse.numberOfPeriods != null) rapidApiOpenWeatherMapDailyForecastResponse.numberOfPeriods!! else 0,
        rapidApiOpenWeatherMapDailyForecastResponse.city!!.name!!,
        if (rapidApiOpenWeatherMapDailyForecastResponse.city!!.cityId != null)
            rapidApiOpenWeatherMapDailyForecastResponse.city!!.cityId!!
        else Random(RANDOM_SEED).nextLong(),
        forecastType.value
    ) {
        this.city = City(rapidApiOpenWeatherMapDailyForecastResponse.city!!, this.cityId)
    }
}