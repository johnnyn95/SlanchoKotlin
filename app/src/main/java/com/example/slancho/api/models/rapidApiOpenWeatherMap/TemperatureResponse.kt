package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TemperatureResponse(
    @SerializedName("day")
    @Expose var day: Double?,
    @SerializedName("min")
    @Expose var min: Double?,
    @SerializedName("max")
    @Expose var max: Double?,
    @SerializedName("night")
    @Expose var night: Double?,
    @SerializedName("eve")
    @Expose var eve: Double?,
    @SerializedName("morn")
    @Expose var morn: Double?
)
