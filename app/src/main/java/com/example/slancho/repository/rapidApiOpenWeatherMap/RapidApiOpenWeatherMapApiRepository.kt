package com.example.slancho.repository.rapidApiOpenWeatherMap

import com.example.slancho.api.RapidApiOpenWeatherMapService
import com.example.slancho.db.model.Forecast
import com.example.slancho.repository.forecast.ForecastDbRepository
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

    override suspend fun getCurrentForecastByLocation(latitude: Double, longitude: Double) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getCurrentForecast(null, latitude, longitude).execute()
                if (response.isSuccessful) {
                    val forecast =
                        Forecast.createForecastFromCurrentForecastResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
    }

    override suspend fun getCurrentForecastByCityAndCountryCode(location: String) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getCurrentForecast(
                        location, null, null
                    ).execute()
                if (response.isSuccessful) {
                    val forecast =
                        Forecast.createForecastFromCurrentForecastResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
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
                    val forecast =
                        Forecast.createForecastFromRapidApiThreeHourResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
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
                    val forecast =
                        Forecast.createForecastFromRapidApiThreeHourResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
    }

    override suspend fun getRapidApiDailyForecastByLocation(latitude: Double, longitude: Double) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getDailyForecastWeatherData(
                        null, latitude, longitude,
                        sharedPreferencesManager.forecastDataForDaysValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast =
                        Forecast.createForecastFromRapidApiDailyResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
    }

    override suspend fun getRapidApiDailyForecastByCityAndCountryCode(location: String) {
        withContext(IO) {
            try {
                val response =
                    rapidApiOpenWeatherMapService.getDailyForecastWeatherData(
                        location, null, null,
                        sharedPreferencesManager.forecastDataForDaysValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast =
                        Forecast.createForecastFromRapidApiDailyResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
    }
}