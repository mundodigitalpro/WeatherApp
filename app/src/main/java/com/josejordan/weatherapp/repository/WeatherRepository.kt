package com.josejordan.weatherapp.repository

import androidx.lifecycle.LiveData
import com.josejordan.weatherapp.data.WeatherResponse


interface WeatherRepository {
    fun getWeather(city: String, country: String): LiveData<WeatherResponse>
}
