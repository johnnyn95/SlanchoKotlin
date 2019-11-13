package com.example.slancho.ui.main.weather.adapterDelegates

import com.example.slancho.R
import com.example.slancho.common.weatherForecastModels.CurrentWeatherForecast
import com.example.slancho.common.weatherForecastModels.ThreeHourWeatherForecast
import com.example.slancho.common.weatherForecastModels.WeatherForecast
import com.example.slancho.utils.WeatherFormatUtils
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.*

class ThreeHourWeatherForecastAdapterDelegate {
    companion object {
        const val layoutResId = R.layout.list_item_weather_forecast_current
    }

    fun init(
        weatherFormatUtils: WeatherFormatUtils,
        itemClickListener: (ThreeHourWeatherForecast) -> Unit
    ) =
        adapterDelegateLayoutContainer<ThreeHourWeatherForecast, WeatherForecast>(
            layoutResId
        ) {
            grp_content.setOnClickListener { itemClickListener(item) }
            bind {
                txt_temp.text = weatherFormatUtils.formatTemperatureSimple(item.temp)
                txt_date.text = weatherFormatUtils.formatDate(item.dateTime)
                txt_humidity.text =
                    weatherFormatUtils.formatPercentageSimple(item.humidityPercentage)
                txt_clouds.text = weatherFormatUtils.formatPercentageSimple(item.cloudsPercentage)
                txt_min_temp.text = weatherFormatUtils.formatTemperatureSimple(item.tempMin)
                txt_max_tempt.text = weatherFormatUtils.formatTemperatureSimple(item.tempMax)
                txt_wind_speed.text =
                    weatherFormatUtils.formatWindSpeedAndDirection(item.windSpeed, item.windDegrees)
                txt_pressure.text = weatherFormatUtils.formatPressureSimple(item.pressure)
                txt_description.text =
                    weatherFormatUtils.formatDescription(item.info, item.description)
//                txt_sunrise.text = weatherFormatUtils.formatDateTime(item.sunrise)
//                txt_sunset.text = weatherFormatUtils.formatDateTime(item.sunset)
                lottie_weather_icon.setAnimation(weatherFormatUtils.fetchWeatherIcon(item.icon))
            }
        }
}