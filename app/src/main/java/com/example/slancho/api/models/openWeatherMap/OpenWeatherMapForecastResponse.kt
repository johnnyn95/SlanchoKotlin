package com.example.slancho.api.models.openWeatherMap


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OpenWeatherMapForecastResponse(
    @SerializedName("cod")
    @Expose
    var cod: String?,
    @SerializedName("message")
    @Expose
    var message: Double?,
    @SerializedName("cnt")
    @Expose
    var numberOfDays: Int?,
    @SerializedName("list")
    @Expose
    var weatherList: List<WeatherResponse>?,
    @SerializedName("city")
    @Expose
    var city: CityResponse?
)



