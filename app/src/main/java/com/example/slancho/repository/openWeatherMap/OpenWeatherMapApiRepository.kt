package com.example.slancho.repository.openWeatherMap

import com.example.slancho.api.OpenWeatherMapService
import com.example.slancho.db.model.Forecast
import com.example.slancho.repository.forecast.ForecastDbRepository
import com.example.slancho.utils.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class OpenWeatherMapApiRepository @Inject constructor(
    private val openWeatherMapService: OpenWeatherMapService,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val forecastDbRepository: ForecastDbRepository
) : OpenWeatherMapRepository {

    companion object {
        val TAG = OpenWeatherMapApiRepository::class.java.simpleName
    }

    override suspend fun getThreeHourForecastByLocation(latitude: Double, longitude: Double) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getThreeHourForecastForNumberOfPeriods(
                        null, latitude, longitude,
                        sharedPreferencesManager.numberOfPeriodsForThreeHourForecastValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast =
                        Forecast.createForecastFromThreeHourResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
    }

    override suspend fun getThreeHourForecastByCityAndCountryCode(location: String) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getThreeHourForecastForNumberOfPeriods(
                        location, null, null,
                        sharedPreferencesManager.numberOfPeriodsForThreeHourForecastValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast =
                        Forecast.createForecastFromThreeHourResponse(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e("Couldn't fetch the forecast data! $e")
            }
        }
    }

    override suspend fun getCurrentForecastByLocation(latitude: Double, longitude: Double) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getCurrentForecast(null, latitude, longitude).execute()
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
                    openWeatherMapService.getCurrentForecast(
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

}