package com.example.slancho.repository.openWeatherMap

import com.example.slancho.api.OpenWeatherMapService
import com.example.slancho.api.RapidApiOpenWeatherMapService
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
    private val rapidApiOpenWeatherMapService: RapidApiOpenWeatherMapService,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val forecastDbRepository: ForecastDbRepository
) : OpenWeatherMapRepository {

    companion object {
        val TAG = OpenWeatherMapApiRepository::class.java.simpleName
    }

    override suspend fun getForecastWeatherDataByCityAndCountryCode(location: String) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getForecastForNumberOfDays(
                        location, null, null,
                        sharedPreferencesManager.forecastDataForDaysValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast = Forecast(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {

            }
        }
    }

    override suspend fun getForecastWeatherDataByLocation(latitude: Double, longitude: Double) {
        withContext(IO) {
            try {
                val response =
                    openWeatherMapService.getForecastForNumberOfDays(
                        null, latitude, longitude,
                        sharedPreferencesManager.forecastDataForDaysValue
                    ).execute()
                if (response.isSuccessful) {
                    val forecast = Forecast(response.body()!!)
                    forecastDbRepository.insertForecast(forecast)
                }
            } catch (e: IOException) {
                Timber.w(TAG, "Couldn't fetch the forecast data!")
            }
        }
    }

    override suspend fun getRapidApiForecastWeatherData(
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
                    ).execute()
                if (response.isSuccessful) {
                    // TODO parse the data
                }
            } catch (e: IOException) {

            }
        }
    }

}