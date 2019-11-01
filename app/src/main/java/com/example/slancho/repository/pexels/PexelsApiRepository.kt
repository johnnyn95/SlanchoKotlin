package com.example.slancho.repository.pexels

import com.example.slancho.api.PexelsService
import com.example.slancho.api.models.pexels.PexelsImageSearchResponse
import com.example.slancho.db.dao.CityDao
import com.example.slancho.db.model.City
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class PexelsApiRepository @Inject constructor(
    var cityDao: CityDao,
    var pexelsService: PexelsService
) :
    PexelsRepository {
    override suspend fun performPexelsImageSearchForCity(city: City): City {
        return withContext(IO) {
            try {
                // TODO Use city name eventually,currently API doesn't fetch the proper images
                val response =
                    pexelsService.performPexelsImageSearch("weather", null, null).execute()
                if (response.isSuccessful) {
                    city.cityImageUrl =
                        PexelsImageSearchResponse.getRandomImageUrlFromResponse(response.body()!!.photos)
                    cityDao.insert(city)
                    city
                } else city
            } catch (e: IOException) {
                Timber.w("Couldn't fetch city image from Pexels")
                city
            }
        }
    }
}
