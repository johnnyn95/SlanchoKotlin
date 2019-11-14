package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RapidApiOpenWeatherMapCurrentForecastResponse(
    @SerializedName("coord")
    @Expose var coordinatesResponse: CoordinatesResponse?,
    @SerializedName("weather")
    @Expose var weatherInfo: List<WeatherInfoResponse>?,
    @SerializedName("main")
    @Expose var mainResponse: ThreeHourWeatherMainResponse?,
    @SerializedName("visibility")
    @Expose var visibility: Long?,
    @SerializedName("wind")
    @Expose var windResponse: WindResponse?,
    @SerializedName("clouds")
    @Expose var cloudsResponse: CloudsResponse?,
    @SerializedName("dt")
    @Expose var dateTime: Long?,
    @SerializedName("sys")
    @Expose var sysResponse: SysResponse?,
    @SerializedName("timezone")
    @Expose var timezone: Long?,
    @SerializedName("id")
    @Expose var id: Long?,
    @SerializedName("name")
    @Expose var name: String?,
    @SerializedName("cod")
    @Expose var cod: Long?
)



