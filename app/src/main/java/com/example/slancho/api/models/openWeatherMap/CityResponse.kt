package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResponse(
    @SerializedName("geoname_id")
    @Expose
    var cityId: Long?,
    @SerializedName("name")
    @Expose
    var name: String?,
    @SerializedName("coord")
    @Expose
    var coordinatesResponse: CoordinatesResponse?,
    @SerializedName("country")
    @Expose
    var countryCode: String?,
    @SerializedName("sunrise")
    @Expose
    var sunrise: Long?,
    @SerializedName("sunset")
    @Expose
    var sunset: Long?,
    @SerializedName("population")
    @Expose
    var population: Long?,
    @SerializedName("timezone")
    @Expose
    var timezone: Long?
)
