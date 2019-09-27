package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse(
    @SerializedName("dt")
    @Expose
    var dateTime: Long?,
    @SerializedName("main")
    @Expose
    var weatherMain: WeatherMainResponse?,
    @SerializedName("weather")
    @Expose
    var weatherInfo: List<WeatherInfoResponse>?,
    @SerializedName("clouds")
    @Expose
    var clouds: CloudsResponse?,
    @SerializedName("wind")
    @Expose
    var wind: WindResponse?,
    @SerializedName("rain")
    @Expose
    var rain: RainResponse?,
    @SerializedName("dt_txt")
    @Expose
    var dateTimeText: String?
)


