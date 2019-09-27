package com.example.slancho.api.models.openWeatherMap


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("dt")
    @Expose
    var dateTime: Long?,
    @SerializedName("main")
    @Expose
    var weatherMainResponse: WeatherMainResponse?,
    @SerializedName("weatherInfo")
    @Expose
    var weatherInfo: List<WeatherInfoResponse>,
    @SerializedName("cloud")
    @Expose
    var cloudResponse: CloudResponse,
    @SerializedName("wind")
    @Expose
    var windResponse: WindResponse?,
    @SerializedName("dt_txt")
    @Expose
    var dateTimeTxt: String?
)
