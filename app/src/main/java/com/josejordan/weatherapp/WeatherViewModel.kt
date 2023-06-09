package com.josejordan.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun getWeather(city: String, country: String): LiveData<WeatherResponse> {
        return repository.getWeather(city, country)
    }
}

