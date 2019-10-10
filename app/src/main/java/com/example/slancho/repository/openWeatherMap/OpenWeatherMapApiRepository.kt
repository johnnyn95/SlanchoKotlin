package com.example.slancho.repository.openWeatherMap

import com.example.slancho.api.ForecastType
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
                    val forecast = Forecast(response.body()!!, ForecastType.ThreeHour)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e(TAG, "Couldn't fetch the forecast data! $e")
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
                    val forecast = Forecast(response.body()!!, ForecastType.ThreeHour)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.e(TAG, "Couldn't fetch the forecast data! $e")
            }
        }
    }
}