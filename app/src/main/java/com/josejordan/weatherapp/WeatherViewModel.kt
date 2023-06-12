package com.josejordan.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel  @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    fun getWeather(city: String, country: String): LiveData<WeatherResponse> {
        return repository.getWeather(city, country)
    }
}

