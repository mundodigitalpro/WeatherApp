package com.josejordan.weatherapp.repository

import com.josejordan.weatherapp.data.WeatherResponse
import com.josejordan.weatherapp.service.WeatherService
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
): WeatherRepository {

    override suspend fun getWeather(city: String, country: String): WeatherResponse {
            return service.getWeatherByCityAndCountry(location = "$city,$country",units = "metric", app_id = "854adb87b1ddd246adf542c47f3eeca0")
    }
}
