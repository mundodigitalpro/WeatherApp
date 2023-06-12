package com.josejordan.weatherapp.repository

import com.josejordan.weatherapp.data.WeatherResponse
interface WeatherRepository {
    suspend fun getWeather(city: String, country: String): WeatherResponse
}

