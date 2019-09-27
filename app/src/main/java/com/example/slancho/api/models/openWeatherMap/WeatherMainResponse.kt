package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherMainResponse(
    @SerializedName("temp")
    @Expose
    var temp: Double?,
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double?,
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double?,
    @SerializedName("pressure")
    @Expose
    var pressure: Double?,
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Double?,
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Double?,
    @SerializedName("humidity")
    @Expose
    var humidity: Int?,
    @SerializedName("temp_kf")
    @Expose
    var tempKf: Double?
)