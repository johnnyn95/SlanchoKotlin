package com.example.slancho.api.models.pexels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

class PexelsImageSearchResponse {
    companion object {
        fun getRandomImageUrlFromResponse(photos: List<PhotoResponse>?): String =
            if (photos != null) photos[Random.nextInt(
                0,
                photos.size
            )].srcResponse!!.small!! else ""
    }

    @SerializedName("page")
    @Expose
    val page: Long? = null
    @SerializedName("per_page")
    @Expose
    val perPage: Long? = null
    @SerializedName("total_results")
    @Expose
    val totalResults: Long? = null
    @SerializedName("url")
    @Expose
    val url: String? = null
    @SerializedName("next_page")
    @Expose
    val nextPage: String? = null
    @SerializedName("photos")
    @Expose
    val photos: List<PhotoResponse>? = null
}
