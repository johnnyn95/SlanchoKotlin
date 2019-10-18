package com.example.slancho.repository.forecast

import com.example.slancho.db.dao.CityDao
import com.example.slancho.db.dao.ForecastDao
import com.example.slancho.db.dao.ForecastInfoDao
import com.example.slancho.db.model.Forecast
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ForecastDbRepository @Inject constructor(
    var forecastDao: ForecastDao,
    var cityDao: CityDao,
    var forecastInfoDao: ForecastInfoDao
) :
    ForecastRepository {

    companion object {
        val TAG = ForecastDbRepository::class.java.simpleName
    }

    override suspend fun insertForecast(forecast: Forecast) {
        runBlocking(IO) {
            forecastDao.insert(forecast)
            cityDao.insert(forecast.city!!)
            forecastInfoDao.insertForecastInfoList(*forecast.forecastInfo.toTypedArray())
        }
    }

    override suspend fun getLatestForecastByCityName(cityName: String): Forecast {
        return runBlocking(IO) {
            val forecast: Forecast = forecastDao.getLatestForecastByCityName(cityName)
            forecast.city = cityDao.getCityByCityName(forecast.cityName)
            val forecastInfo =
                forecastInfoDao.getLatestForecastDataListByForecastId(forecast.id)
                    .toCollection(ArrayList())
            forecast.forecastInfo.addAll(forecastInfo)
            forecast
        }
    }

    override suspend fun getLatestForecastByLatLong(lat: Double, long: Double): Forecast {
        return runBlocking(IO) {
            val city = cityDao.getCityByLatLong(lat, long)
            val forecast: Forecast = forecastDao.getLatestForecastByCityId(city.id)
            forecast.city = city
            val forecastInfo =
                forecastInfoDao.getLatestForecastDataListByForecastId(forecast.id)
                    .toCollection(ArrayList())
            forecast.forecastInfo.addAll(forecastInfo)
            forecast
        }
    }
}
