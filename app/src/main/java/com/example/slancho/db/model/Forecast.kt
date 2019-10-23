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
import kotlin.collections.ArrayList

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
    @ColumnInfo(name = "fetchedForNumberOfPeriods")
    val fetchedForNumberOfPeriods: Int,
    @ColumnInfo(name = "cityName")
    val cityName: String,
    @ColumnInfo(name = "cityId")
    val cityId: Long,
    @ColumnInfo(name = "forecastType")
    val forecastType: String
) {
    @Ignore
    var city: City? = null

    @Ignore
    var forecastInfo: ArrayList<ForecastInfo> = ArrayList()

    companion object {
        /**
         * Existing City Id +1 in order not to "randomly" create existing city
         */
        const val RANDOM_SEED: Long = 7270111

        /**
         * Used for the Daily forecast by the RapidApiOpenWeatherApi
         */
        fun createForecastFromRapidApiDailyResponse(
            rapidApiOpenWeatherMapDailyForecastResponse:
            RapidApiOpenWeatherMapDailyForecastResponse
        ): Forecast {
            val forecast =
                Forecast(
                    UUID.randomUUID().toString(),
                    System.currentTimeMillis(),
                    rapidApiOpenWeatherMapDailyForecastResponse.cod!!,
                    rapidApiOpenWeatherMapDailyForecastResponse.message!!,
                    if (rapidApiOpenWeatherMapDailyForecastResponse.numberOfPeriods != null)
                        rapidApiOpenWeatherMapDailyForecastResponse.numberOfPeriods!! else 0,
                    rapidApiOpenWeatherMapDailyForecastResponse.city!!.name!!,
                    if (rapidApiOpenWeatherMapDailyForecastResponse.city!!.cityId != null)
                        rapidApiOpenWeatherMapDailyForecastResponse.city!!.cityId!!
                    else Random(RANDOM_SEED).nextLong(), ForecastType.Daily.value
                )
            forecast.city =
                City.createCityFromRapidApiCityResponse(
                    rapidApiOpenWeatherMapDailyForecastResponse.city!!,
                    forecast.cityId
                )
            val forecastInfo = arrayListOf<ForecastInfo>()
            rapidApiOpenWeatherMapDailyForecastResponse.dailyWeatherList!!.stream().forEach {
                forecastInfo.add(
                    ForecastInfo.createForecastInfoFromRapidApiDailyResponse(
                        it,
                        forecast.id
                    )
                )
            }
            forecast.forecastInfo = forecastInfo
            return forecast
        }

        /**
         * Used for the Three hour forecast by the RapidApiOpenWeather Api
         */
        fun createForecastFromRapidApiThreeHourResponse(
            rapidApiThreeHourForecastResponse:
            RapidApiOpenWeatherMapTheeHourForecastResponse
        ): Forecast {

            val forecast = Forecast(
                UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                rapidApiThreeHourForecastResponse.cod!!,
                rapidApiThreeHourForecastResponse.message!!,
                if (rapidApiThreeHourForecastResponse.numberOfPeriods != null)
                    rapidApiThreeHourForecastResponse.numberOfPeriods!! else 0,
                rapidApiThreeHourForecastResponse.city!!.name!!,
                if (rapidApiThreeHourForecastResponse.city!!.cityId != null)
                    rapidApiThreeHourForecastResponse.city!!.cityId!!
                else Random(RANDOM_SEED).nextLong(),
                ForecastType.ThreeHour.value
            )
            forecast.city = City.createCityFromRapidApiCityResponse(
                rapidApiThreeHourForecastResponse.city!!,
                forecast.cityId
            )
            val forecastInfo = arrayListOf<ForecastInfo>()
            rapidApiThreeHourForecastResponse.threeHourWeatherList!!.stream().forEach {
                forecastInfo.add(
                    ForecastInfo.createForecastInfoFromRapidApiThreeHourResponse(
                        it,
                        forecast.id
                    )
                )
            }
            forecast.forecastInfo = forecastInfo
            return forecast
        }

        /**
         * Used for the Three hour forecast by the RapidApiOpenWeather Api
         */
        fun createForecastFromThreeHourResponse(
            openWeatherMapThreeHourForecastResponse:
            OpenWeatherMapThreeHourForecastResponse
        ): Forecast {
            val forecast = Forecast(
                UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                openWeatherMapThreeHourForecastResponse.cod!!,
                openWeatherMapThreeHourForecastResponse.message!!,
                if (openWeatherMapThreeHourForecastResponse.numberOfPeriods != null)
                    openWeatherMapThreeHourForecastResponse.numberOfPeriods!! else 0,
                openWeatherMapThreeHourForecastResponse.city!!.name!!,
                if (openWeatherMapThreeHourForecastResponse.city!!.cityId != null)
                    openWeatherMapThreeHourForecastResponse.city!!.cityId!!
                else Random(RANDOM_SEED).nextLong(),
                ForecastType.ThreeHour.value
            )
            forecast.city = City.createCityFromCityResponse(
                openWeatherMapThreeHourForecastResponse.city!!,
                forecast.cityId
            )
            val forecastInfo = arrayListOf<ForecastInfo>()
            openWeatherMapThreeHourForecastResponse.threeHourWeatherList!!.stream().forEach {
                forecastInfo.add(
                    ForecastInfo.createForecastInfoFromThreeHourResponse(
                        it,
                        forecast.id
                    )
                )
            }
            forecast.forecastInfo = forecastInfo
            return forecast
        }
    }
}