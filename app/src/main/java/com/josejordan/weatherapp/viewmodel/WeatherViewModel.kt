package com.josejordan.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josejordan.weatherapp.data.WeatherResponse
import com.josejordan.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    // Add an error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _weatherResponse: MutableStateFlow<WeatherResponse?> = MutableStateFlow(null)
    val weatherResponse: StateFlow<WeatherResponse?> = _weatherResponse

    fun getWeather(city: String, country: String) {
        viewModelScope.launch {

         try {
             val response = repository.getWeather(city, country)
             _weatherResponse.value = response

         }catch (e: Exception){
             // Handle the exception and set the error state
             _error.value = e.message
         }

        }
    }
}



