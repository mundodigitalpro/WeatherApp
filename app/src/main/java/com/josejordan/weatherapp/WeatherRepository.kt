package com.josejordan.weatherapp

import androidx.lifecycle.LiveData

interface WeatherRepository {
    fun getWeather(city: String, country: String): LiveData<WeatherResponse>
}
