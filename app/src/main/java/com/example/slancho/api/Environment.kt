package com.example.slancho.api

interface Environment {
    enum class Environments : Environment {
        DEV {
            override fun getRapidApiOpenWeatherMapApiUrl(): String =
                "https://community-open-weather-map.p.rapidapi.com/"

            override fun getRapidApiOpenWeatherMapKey(): String =
                "b3428ecd82msh318a7a12b562910p1d3125jsne4c7a43ba7cf"

            override fun getOpenWeatherMapApiUrl(): String =
                "http://api.openweathermap.org/data/2.5/"

            override fun getOpenWeatherMapKey(): String =
                "1dfb36c4d302f2169ffd9a9df76411cf"

            override fun getPexelsApiUrl(): String =

                "https://api.pexels.com/v1/"

            override fun getPexelsKey(): String =
                "563492ad6f91700001000001381cd3af5818477db81f77a5cce4df49"
        },
        PROD {
            override fun getRapidApiOpenWeatherMapApiUrl(): String =
                "https://community-open-weather-map.p.rapidapi.com/"

            override fun getRapidApiOpenWeatherMapKey(): String =
                "b3428ecd82msh318a7a12b562910p1d3125jsne4c7a43ba7cf"

            override fun getOpenWeatherMapApiUrl(): String =
                "http://api.openweathermap.org/data/2.5/"

            override fun getOpenWeatherMapKey(): String =
                "1dfb36c4d302f2169ffd9a9df76411cf"

            override fun getPexelsApiUrl(): String =
                "https://api.pexels.com/v1/"

            override fun getPexelsKey(): String =
                "563492ad6f91700001000001381cd3af5818477db81f77a5cce4df49"
        }
    }

    fun getRapidApiOpenWeatherMapApiUrl(): String

    fun getRapidApiOpenWeatherMapKey(): String

    fun getOpenWeatherMapApiUrl(): String

    fun getOpenWeatherMapKey(): String

    fun getPexelsApiUrl(): String

    fun getPexelsKey(): String
}