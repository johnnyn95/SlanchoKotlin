package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThreeHourWeatherResponse(
    @SerializedName("dt")
    @Expose var dateTime: Long?,
    @SerializedName("main")
    @Expose var threeHourWeatherMainResponse: ThreeHourWeatherMainResponse?,
    @SerializedName("weather")
    @Expose var weatherInfo: List<WeatherInfoResponse>?,
    @SerializedName("clouds")
    @Expose var cloudsResponse: CloudsResponse?,
    @SerializedName("wind")
    @Expose var windResponse: WindResponse?,
    @SerializedName("rain")
    @Expose var rainResponse: RainResponse?,
    @SerializedName("dt_txt")
    @Expose var dateTimeTxt: String?
)


