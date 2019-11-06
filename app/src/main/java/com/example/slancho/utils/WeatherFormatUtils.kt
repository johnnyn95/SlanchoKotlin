package com.example.slancho.utils

import kotlin.math.roundToInt

class WeatherFormatUtils {
    companion object {
        // TODO Could implement translatable directions and icons
        val directions =
            arrayListOf("↑ N", "↗ NE", "→ E", "↘ SE", "↓ S", "↙ SW", "← W", "↖ NW")

    }

    private fun getCardinalDirection(angle: Double): String =
        directions[(angle / 45).roundToInt() % 8]

    fun formatWindSpeedAndDirection(windSpeed: Double, windDegree: Double) = ""
}