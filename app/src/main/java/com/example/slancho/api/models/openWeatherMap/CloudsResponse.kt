package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CloudsResponse(
    @SerializedName("all")
    @Expose var all: Double?
)