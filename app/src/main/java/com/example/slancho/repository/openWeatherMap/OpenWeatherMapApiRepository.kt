package com.example.slancho.repository.openWeatherMap

import com.example.slancho.api.OpenWeatherMapService
import com.example.slancho.api.RapidApiOpenWeatherMapService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class OpenWeatherMapApiRepository @Inject constructor(
    private val openWeatherMapService: OpenWeatherMapService,
    private val rapidApiOpenWeatherMapService: RapidApiOpenWeatherMapService
) : OpenWeatherMapRepository {

    override suspend fun getForecastForXDaysByCityName(location: String, numberOfDays: Int) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getForecastForXDaysByCityName(location, numberOfDays)
                        .execute()
                if (response.isSuccessful) {
                    // TODO parse the data
                }
            } catch (e: IOException) {

            }
        }
    }

    override suspend fun getForecastWeatherData(
        location: String,
        latitude: Double,
        longitude: Double
    ) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getForecastWeatherData(
                        location,
                        latitude,
                        longitude
                    )
                        .execute()
                if (response.isSuccessful) {
                    // TODO parse the data
                }
            } catch (e: IOException) {

            }
        }
    }

}