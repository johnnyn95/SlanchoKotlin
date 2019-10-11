package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ThreeHourWeatherResponse(
    @SerializedName("dt")
    @Expose var dateTime: Long?,
    @SerializedName("main")
    @Expose var threeHourWeatherMain: ThreeHourWeatherMainResponse?,
    @SerializedName("weather")
    @Expose var weatherInfo: List<WeatherInfoResponse>?,
    @SerializedName("clouds")
    @Expose var clouds: CloudsResponse?,
    @SerializedName("wind")
    @Expose var wind: WindResponse?,
    @SerializedName("rain")
    @Expose var rain: RainResponse?,
    @SerializedName("dt_txt")
    @Expose var dateTimeText: String?
)


