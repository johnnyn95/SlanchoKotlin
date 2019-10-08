package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CityResponse(
    @SerializedName("id")
    @Expose var id: Long?,
    @SerializedName("name")
    @Expose var name: String,
    @SerializedName("coord")
    @Expose var coordinatesResponse: CoordinatesResponse,
    @SerializedName("country")
    @Expose var country: String,
    @SerializedName("population")
    @Expose var population: Long?,
    @SerializedName("timezone")
    @Expose var timezone: Long?,
    @SerializedName("sunrise")
    @Expose var sunrise: Long?,
    @SerializedName("sunset")
    @Expose var sunset: Double?
)
