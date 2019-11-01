package com.example.slancho.api

import com.example.slancho.api.models.pexels.PexelsImageSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsService {
    @GET("search")
    fun performPexelsImageSearch(
        @Query("query") query: String,
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?
    ): Call<PexelsImageSearchResponse>
}