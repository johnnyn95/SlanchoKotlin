package com.example.slancho.api.models.pexels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("width")
    @Expose
    val width: Long? = null
    @SerializedName("height")
    @Expose
    val height: Long? = null
    @SerializedName("url")
    @Expose
    val url: String? = null
    @SerializedName("photographer")
    @Expose
    val photographer: String? = null
    @SerializedName("src")
    @Expose
    val srcResponse: SrcResponse? = null
}
