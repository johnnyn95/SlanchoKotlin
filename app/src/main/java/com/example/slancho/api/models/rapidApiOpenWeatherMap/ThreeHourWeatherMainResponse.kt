package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThreeHourWeatherMainResponse(
    @SerializedName("temp")
    @Expose var temp: Double?,
    @SerializedName("temp_min")
    @Expose var tempMin: Double?,
    @SerializedName("temp_max")
    @Expose var tempMax: Double?,
    @SerializedName("pressure")
    @Expose var pressure: Double?,
    @SerializedName("sea_level")
    @Expose var seaLevel: Double?,
    @SerializedName("grnd_level")
    @Expose var grndLevel: Double?,
    @SerializedName("humidity")
    @Expose var humidity: Double?,
    @SerializedName("temp_kf")
    @Expose var tempKf: Double?
)
