package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoordinatesResponse(
    @SerializedName("lat")
    @Expose var lat: Double?,
    @SerializedName("lon")
    @Expose var lon: Double? )