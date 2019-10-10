package com.example.slancho.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    /**
     * Used by OpenWeatherMap Api
     */
    constructor(
        cityResponse: com.example.slancho.api.models.openWeatherMap.CityResponse,
        cityId: Long
    ) : this(
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

    /**
     * Used by RapidApiOpenWeatherMap Api
     */
    constructor(
        cityResponse: com.example.slancho.api.models.rapidApiOpenWeatherMap.CityResponse,
        cityId: Long
    ) : this(
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