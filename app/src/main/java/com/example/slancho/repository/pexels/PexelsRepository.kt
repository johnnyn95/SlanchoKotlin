package com.example.slancho.repository.pexels

import com.example.slancho.db.model.City

interface PexelsRepository {
    suspend fun performPexelsImageSearchForCity(city: City) : City
}