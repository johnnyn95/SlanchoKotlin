package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WindResponse(
    @SerializedName("speed")
    @Expose var speed: Double?,
    @SerializedName("deg")
    @Expose var deg: Double?
)
