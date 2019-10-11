package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OpenWeatherMapThreeHourForecastResponse(
    @SerializedName("cod")
    @Expose var cod: String?,
    @SerializedName("message")
    @Expose var message: Double?,
    @SerializedName("numberOfPeriods")
    @Expose var numberOfPeriods: Int?,
    @SerializedName("city")
    @Expose var city: CityResponse?,
    @SerializedName("list")
    @Expose var threeHourWeatherList: List<ThreeHourWeatherResponse>?
)



