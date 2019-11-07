package com.example.slancho.utils

import android.annotation.SuppressLint
import com.example.slancho.R
import com.example.slancho.api.TemperatureUnit
import com.example.slancho.api.WindUnit
import org.joda.time.DateTime
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

class WeatherFormatUtils(val sharedPreferencesManager: SharedPreferencesManager) {
    companion object {
        // TODO Could implement translatable directions and icons
        private val directions =
            arrayListOf("↑ N", "↗ NE", "→ E", "↘ SE", "↓ S", "↙ SW", "← W", "↖ NW")

        const val temperatureSymbol = "°"

        const val percentageSymbol = "%"

        const val pressureUnit = "hPa"

        val temperatureDecimalFormat = DecimalFormat(".#")

        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("dd/MM")

        @SuppressLint("SimpleDateFormat")
        val dateTimeFormat = SimpleDateFormat("HH:mm dd/MM")

        fun getCardinalDirection(angle: Double): String =
            directions[(angle / 45).roundToInt() % 8]


    }

    fun formatPercentageSimple(percentage: Double) = "${percentage.toInt()}$percentageSymbol"

    fun formatWindSpeedAndDirection(windSpeed: Double, windDegree: Double) =
        " $windSpeed ${chooseWindUnit()} ${getCardinalDirection(windDegree)}"

    fun formatTemperature(temperature: Double) =
        "$temperatureSymbol${temperatureDecimalFormat.format(temperature)}"

    fun formatTemperatureSimple(temperature: Double) = "$temperatureSymbol${temperature.toInt()}"

    @SuppressLint("DefaultLocale")
    fun formatDescription(info: String, description: String) = "$info: ${description.capitalize()}"

    private fun chooseWindUnit() =
        if (sharedPreferencesManager.tempUnitValue == TemperatureUnit.Fahrenheit.value)
            WindUnit.Imperial.value else WindUnit.Metric.value

    fun formatDate(dateTime: DateTime) =
        dateFormat.format(dateTime.toDate())!!

    fun formatDateTime(dateTime: DateTime) =
        dateTimeFormat.format(dateTime.toDate())!!

    fun formatPressure(pressure: Double) = "$pressure $pressureUnit"

    fun formatPressureSimple(pressure: Double) = "${pressure.toInt()} $pressureUnit"

    fun fetchWeatherIcon(iconId: String) =
        when (iconId) {
            "01d" -> R.raw.ic_01d
            "01n" -> R.raw.ic_01n
            "02d" -> R.raw.ic_02d
            "02n" -> R.raw.ic_02n
            "03d" -> R.raw.ic_03d
            "03n" -> R.raw.ic_03d
            "04d" -> R.raw.ic_04d
            "04n" -> R.raw.ic_04n
            "09d" -> R.raw.ic_09d
            "09n" -> R.raw.ic_09n
            "11d" -> R.raw.ic_11d
            "11n" -> R.raw.ic_11n
            "13d" -> R.raw.ic_13d
            "13n" -> R.raw.ic_13n
            "50d" -> R.raw.ic_50d
            "50n" -> R.raw.ic_50n
            else -> R.raw.ic_01d
        }
}