package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThreeHourWeatherResponse(
    @SerializedName("dt")
    @Expose var dateTime: Long?,
    @SerializedName("main")
    @Expose var threeHourWeatherMainResponse: ThreeHourWeatherMainResponse?,
    @SerializedName("weatherInfo")
    @Expose var weatherInfo: List<WeatherInfoResponse>,
    @SerializedName("cloud")
    @Expose var cloudsResponse: CloudsResponse,
    @SerializedName("wind")
    @Expose var windResponse: WindResponse?,
    @SerializedName("dt_txt")
    @Expose var dateTimeTxt: String?
)
