package com.example.slancho.ui.main.weather

import androidx.lifecycle.MutableLiveData
import androidx.work.WorkManager
import com.example.slancho.api.ForecastType
import com.example.slancho.db.model.City
import com.example.slancho.db.model.Forecast
import com.example.slancho.db.model.User
import com.example.slancho.repository.forecast.ForecastDbRepository
import com.example.slancho.repository.openWeatherMap.OpenWeatherMapApiRepository
import com.example.slancho.repository.pexels.PexelsApiRepository
import com.example.slancho.repository.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapApiRepository
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.main.BaseMainFragmentViewModel
import com.example.slancho.utils.LocationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class WeatherFragmentViewModel @Inject constructor(
    locationManager: LocationManager,
    userDbRepository: UserDbRepository,
    workManager: WorkManager,
    private var openWeatherMapApiRepository: OpenWeatherMapApiRepository,
    private var rapidApiOpenWeatherMapRepository: RapidApiOpenWeatherMapApiRepository,
    private var forecastDbRepository: ForecastDbRepository,
    private var pexelsApiRepository: PexelsApiRepository

) : BaseMainFragmentViewModel(locationManager, userDbRepository, workManager) {

    override val TAG: String = WeatherFragmentViewModel::javaClass.name
    private lateinit var currentUser: User

    private var currentForecastType = ForecastType.Current

    val currentForecast: MutableLiveData<Forecast> by lazy {
        MutableLiveData<Forecast>()
    }

    override fun onScreenReady(user: User) {
        CoroutineScope(IO).launch {
            currentUser = user
            fetchForecastData(user)
            onFilterButtonClicked(currentForecastType)
        }
    }

    private suspend fun fetchForecastData(user: User) {
        rapidApiOpenWeatherMapRepository.getCurrentForecastByCityAndCountryCode(
            user.lastKnownLocation!!.getFormattedCityAndCountryCode()
        )

        rapidApiOpenWeatherMapRepository.getRapidApiDailyForecastByCityAndCountryCode(
            user.lastKnownLocation!!.getFormattedCityAndCountryCode()
        )

        rapidApiOpenWeatherMapRepository.getRapidApiThreeHourForecastByCityAndCountryCode(
            user.lastKnownLocation!!.getFormattedCityAndCountryCode()
        )
    }

    private fun fetchForecast() {
        CoroutineScope(IO).launch {
            try {
                val forecast =
                    forecastDbRepository.getLatestForecastByCityNameAndForecastType(
                        currentUser.lastKnownLocation!!.city,
                        currentForecastType
                    )
                forecast.city = fetchForecastCityImage(forecast.city!!)
                currentForecast.postValue(forecast)

            } catch (e: NullPointerException) {
                try {
                    val forecast = forecastDbRepository.getLatestForecastByLatLongAndForecastType(
                        currentUser.lastKnownLocation!!.latitude,
                        currentUser.lastKnownLocation!!.longitude, currentForecastType
                    )
                    forecast.city = fetchForecastCityImage(forecast.city!!)
                    currentForecast.postValue(forecast)
                } catch (e: java.lang.NullPointerException) {
                    Timber.w("Couldn't fetch forecast!")
                }
            }
        }
    }

    private suspend fun fetchForecastCityImage(city: City): City {
        return pexelsApiRepository.performPexelsImageSearchForCity(city)
    }

    fun onFilterButtonClicked(forecastType: ForecastType) {
        currentForecastType = forecastType
        fetchForecast()
    }
}