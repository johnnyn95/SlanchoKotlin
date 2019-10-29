package com.example.slancho.api.models.openWeatherMap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SysResponse(
    @SerializedName("type")
    @Expose var type: Long?,
    @SerializedName("id")
    @Expose var id: Long?,
    @SerializedName("country")
    @Expose var country: String?,
    @SerializedName("sunrise")
    @Expose var sunrise: Long?,
    @SerializedName("sunset")
    @Expose var sunset: Long?
)