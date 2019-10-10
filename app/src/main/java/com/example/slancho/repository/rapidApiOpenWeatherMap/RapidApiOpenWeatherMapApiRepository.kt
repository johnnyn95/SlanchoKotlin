package com.example.slancho.repository.rapidApiOpenWeatherMap

import com.example.slancho.api.ForecastType
import com.example.slancho.api.RapidApiOpenWeatherMapService
import com.example.slancho.db.model.Forecast
import com.example.slancho.repository.forecast.ForecastDbRepository
import com.example.slancho.repository.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapRepository
import com.example.slancho.utils.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class RapidApiOpenWeatherMapApiRepository @Inject constructor(
    private val rapidApiOpenWeatherMapService: RapidApiOpenWeatherMapService,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val forecastDbRepository: ForecastDbRepository
) : RapidApiOpenWeatherMapRepository {

    companion object {
        val TAG = RapidApiOpenWeatherMapApiRepository::class.java.simpleName
    }

    override suspend fun getRapidApiThreeHourForecastByLocation(
        latitude: Double,
        longitude: Double
    ) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getThreeHourForecastWeatherData(
                        null, latitude, longitude,
                        sharedPreferencesManager.numberOfPeriodsForThreeHourForecastValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast = Forecast(response.body()!!, ForecastType.ThreeHour)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e(TAG, "Couldn't fetch the forecast data! $e")
            }
        }
    }

    override suspend fun getRapidApiThreeHourForecastByCityAndCountryCode(location: String) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getThreeHourForecastWeatherData(
                        location, null, null,
                        sharedPreferencesManager.numberOfPeriodsForThreeHourForecastValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast = Forecast(response.body()!!, ForecastType.ThreeHour)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e(TAG, "Couldn't fetch the forecast data! $e")
            }
        }
    }
}