package com.example.slancho.api.models.rapidApiOpenWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DailyWeatherResponse(
    @SerializedName("dt")
    @Expose var dateTime: Long?,
    @SerializedName("sunrise")
    @Expose var sunrise: Long?,
    @SerializedName("sunset")
    @Expose var sunset: Long?,
    @SerializedName("temp")
    @Expose var temperatureResponse: TemperatureResponse,
    @SerializedName("pressure")
    @Expose var pressure: Double?,
    @SerializedName("humidity")
    @Expose var humidity: Double?,
    @SerializedName("weather")
    @Expose var weatherInfo: List<WeatherInfoResponse>?,
    @SerializedName("speed")
    @Expose var speed: Double?,
    @SerializedName("deg")
    @Expose var deg: Double?,
    @SerializedName("cloud")
    @Expose var cloud: Double?
)


