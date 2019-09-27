package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CloudResponse(
    @SerializedName("all")
    @Expose
    var snow: Double?
)