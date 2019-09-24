package com.example.slancho.api

interface Environment {
    enum class Environments : Environment {
        DEV {
            override fun getOpenWeatherMapApiUrl(): String =
                "https://community-open-weather-map.p.rapidapi.com/"


            override fun getOpenWeatherMapKey(): String =
                "b3428ecd82msh318a7a12b562910p1d3125jsne4c7a43ba7cf"

        },
        PROD {

            override fun getOpenWeatherMapApiUrl(): String =
                "https://community-open-weather-map.p.rapidapi.com/"

            override fun getOpenWeatherMapKey(): String =
                "b3428ecd82msh318a7a12b562910p1d3125jsne4c7a43ba7cf"
        }
    }

    fun getOpenWeatherMapApiUrl(): String

    fun getOpenWeatherMapKey(): String
}