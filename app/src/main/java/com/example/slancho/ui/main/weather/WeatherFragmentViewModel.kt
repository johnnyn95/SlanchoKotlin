package com.example.slancho.ui.main.weather

import androidx.lifecycle.MutableLiveData
import androidx.work.WorkManager
import com.example.slancho.db.model.Forecast
import com.example.slancho.db.model.User
import com.example.slancho.repository.forecast.ForecastDbRepository
import com.example.slancho.repository.openWeatherMap.OpenWeatherMapApiRepository
import com.example.slancho.repository.rapidApiOpenWeatherMap.RapidApiOpenWeatherMapApiRepository
import com.example.slancho.repository.user.UserDbRepository
import com.example.slancho.ui.main.BaseMainFragmentViewModel
import com.example.slancho.utils.LocationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherFragmentViewModel @Inject constructor(
    locationManager: LocationManager,
    userDbRepository: UserDbRepository,
    workManager: WorkManager,
    private var openWeatherMapApiRepository: OpenWeatherMapApiRepository,
    private var rapidApiOpenWeatherMapRepository: RapidApiOpenWeatherMapApiRepository,
    private var forecastDbRepository: ForecastDbRepository

) : BaseMainFragmentViewModel(locationManager, userDbRepository, workManager) {

    override val TAG: String = WeatherFragmentViewModel::javaClass.name

    val forecast: MutableLiveData<Forecast> by lazy {
        MutableLiveData<Forecast>()
    }

    override fun onScreenReady(user: User) {
        CoroutineScope(IO).launch {
            fetchForecastData(user)
        }
    }

    private suspend fun fetchForecastData(user: User) {
        openWeatherMapApiRepository.getCurrentForecastByLocation(
            user.lastKnownLocation!!.latitude,
            user.lastKnownLocation!!.longitude
        )

        rapidApiOpenWeatherMapRepository
            .getRapidApiDailyForecastByCityAndCountryCode(
                user.lastKnownLocation!!
                    .getFormattedCityAndCountryCode()
            )

        val forecast =
            forecastDbRepository.getLatestForecastByCityName(user.lastKnownLocation!!.city)
        this.forecast.postValue(forecast)
    }

}