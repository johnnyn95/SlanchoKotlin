package com.example.slancho.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.slancho.api.models.openWeatherMap.OpenWeatherMapCurrentForecastResponse

@Entity(tableName = "city")
data class City(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "latitude")
    val latitude: Double?,
    @ColumnInfo(name = "longitude")
    val longitude: Double?,
    @ColumnInfo(name = "countryCode")
    val countryCode: String?,
    @ColumnInfo(name = "population")
    val population: Long?,
    @ColumnInfo(name = "timezone")
    val timezone: Long?,
    @ColumnInfo(name = "sunrise")
    val sunrise: Long?,
    @ColumnInfo(name = "sunset")
    val sunset: Long?
) {
    companion object {
        /**
         * Used for the creation of City object from CityResponse
         */
        fun createCityFromCityResponse(
            cityResponse:
            com.example.slancho.api.models.openWeatherMap.CityResponse,
            cityId: Long
        ): City {
            return City(
                cityId,
                cityResponse.name!!,
                cityResponse.coordinatesResponse!!.lat,
                cityResponse.coordinatesResponse!!.lon,
                cityResponse.countryCode,
                if (cityResponse.population != null) cityResponse.population else 0,
                cityResponse.timezone,
                cityResponse.sunrise,
                cityResponse.sunset
            )
        }

        /**
         * Used for the creation of City object from RapidApi CityResponse
         */
        fun createCityFromRapidApiCityResponse(
            cityResponse:
            com.example.slancho.api.models.rapidApiOpenWeatherMap.CityResponse,
            cityId: Long
        ): City {
            return City(
                cityId,
                cityResponse.name!!,
                cityResponse.coordinatesResponse!!.lat,
                cityResponse.coordinatesResponse!!.lon,
                cityResponse.countryCode,
                if (cityResponse.population != null) cityResponse.population else 0,
                cityResponse.timezone,
                cityResponse.sunrise,
                cityResponse.sunset
            )
        }

        /**
         * Used for the creation of City object from CurrentForecastResponse
         */
        fun createCityFromCurrentForecastResponse(
            currentForecastResponse: OpenWeatherMapCurrentForecastResponse,
            cityId: Long
        ): City {
            return City(
                cityId,
                currentForecastResponse.name!!,
                currentForecastResponse.coordinatesResponse!!.lat,
                currentForecastResponse.coordinatesResponse!!.lon,
                currentForecastResponse.sysResponse!!.country,
                0,
                currentForecastResponse.timezone,
                currentForecastResponse.sysResponse!!.sunrise,
                currentForecastResponse.sysResponse!!.sunset
            )
        }
    }
}