package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RapidApiOpenWeatherMapDailyForecastResponse(
    @SerializedName("cod")
    @Expose var cod: String?,
    @SerializedName("message")
    @Expose var message: Double?,
    @SerializedName("city")
    @Expose var city: CityResponse?,
    @SerializedName("cnt")
    @Expose var numberOfPeriods: Int? = null,
    @SerializedName("list")
    @Expose var dailyWeatherList: List<DailyWeatherResponse>?
)

