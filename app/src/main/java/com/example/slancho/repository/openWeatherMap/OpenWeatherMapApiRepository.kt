package com.example.slancho.repository.openWeatherMap

import com.example.slancho.api.OpenWeatherMapService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class OpenWeatherMapApiRepository @Inject constructor(private val openWeatherMapService: OpenWeatherMapService) :
    OpenWeatherMapRepository {
    override suspend fun getForecastWeatherData(
        location: String,
        latitude: Double,
        longitude: Double
    ) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getForecastWeatherData(location, latitude, longitude)
                        .execute()
                if (response.isSuccessful) {
                    // TODO parse the data
                }
            } catch (e: IOException) {

            }
        }
    }

}