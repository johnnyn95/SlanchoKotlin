package com.example.slancho.ui.main.weather.adapterDelegates

import android.view.View.GONE
import android.view.View.VISIBLE
import com.example.slancho.R
import com.example.slancho.common.weatherForecastModels.AdapterCard
import com.example.slancho.common.weatherForecastModels.DailyWeatherForecast
import com.example.slancho.utils.WeatherFormatUtils
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.grp_content
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.lottie_weather_icon
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_clouds
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_date
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_description
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_humidity
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_max_tempt
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_min_temp
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_pressure
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_sunrise
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_sunset
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_temp
import kotlinx.android.synthetic.main.list_item_weather_forecast_current.txt_wind_speed
import kotlinx.android.synthetic.main.list_item_weather_forecast_daily.*

class DailyWeatherForecastAdapterDelegate {
    companion object {
        const val layoutResId = R.layout.list_item_weather_forecast_daily
    }

    fun init(
        weatherFormatUtils: WeatherFormatUtils,
        itemClickListener: (DailyWeatherForecast) -> Unit
    ) =
        adapterDelegateLayoutContainer<DailyWeatherForecast, AdapterCard>(
            layoutResId
        ) {
            grp_content.setOnClickListener { itemClickListener(item) }
            bind {
                txt_temp.text = weatherFormatUtils.formatTemperatureSimple(item.temp)
                txt_date.text = weatherFormatUtils.formatDate(item.dateTime)
                txt_weekday.text = weatherFormatUtils.formatDateTimeWeekday(item.dateTime)
                txt_humidity.text =
                    weatherFormatUtils.formatPercentageSimple(item.humidityPercentage)
                txt_clouds.text = weatherFormatUtils.formatPercentageSimple(item.cloudsPercentage)
                txt_min_temp.text = weatherFormatUtils.formatTemperatureSimple(item.tempMin)
                txt_max_tempt.text = weatherFormatUtils.formatTemperatureSimple(item.tempMax)
                txt_wind_speed.text =
                    weatherFormatUtils.formatWindSpeedAndDirection(item.windSpeed, item.windDegrees)
                txt_pressure.text = weatherFormatUtils.formatPressureSimple(item.pressure)
                txt_description.text =
                    weatherFormatUtils.formatDescription(item.description)
                txt_sunrise.text = weatherFormatUtils.formatDateTime(item.sunrise)
                txt_sunset.text = weatherFormatUtils.formatDateTime(item.sunset)
                txt_temp_morn_value.text =
                    weatherFormatUtils.formatTemperatureSimple(item.tempMorning)
                txt_temp_eve_value.text =
                    weatherFormatUtils.formatTemperatureSimple(item.tempEvening)
                txt_temp_night_value.text =
                    weatherFormatUtils.formatTemperatureSimple(item.tempNight)
                lottie_weather_icon.setAnimation(weatherFormatUtils.fetchWeatherIcon(item.icon))
                grp_additional_details.visibility = if (item.collapsedCard) GONE else VISIBLE
            }
        }
}