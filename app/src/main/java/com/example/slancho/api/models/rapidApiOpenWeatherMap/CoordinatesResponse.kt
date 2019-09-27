package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoordinatesResponse(
    @SerializedName("lon")
    @Expose var lon: Double?,
    @SerializedName("lat")
    @Expose var lat: Double?
)
