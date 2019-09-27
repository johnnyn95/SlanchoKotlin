package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherInfoResponse(
    @SerializedName("id")
    @Expose
    var id: Long?,
    @SerializedName("main")
    @Expose
    var main: String?,
    @SerializedName("description")
    @Expose
    var description: String?,
    @SerializedName("icon")
    @Expose
    var icon: String?
)
