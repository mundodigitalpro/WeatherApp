package com.josejordan.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josejordan.weatherapp.data.WeatherResponse
import com.josejordan.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel  @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    fun getWeather(city: String, country: String) {
        viewModelScope.launch {
            try {
                val weatherResponse = repository.getWeather(city, country)
                _weather.value = weatherResponse
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}


