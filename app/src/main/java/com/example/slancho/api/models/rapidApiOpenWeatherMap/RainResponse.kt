package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RainResponse(
    @SerializedName("3h")
    @Expose var precipitation: Double?
)
