package com.josejordan.weatherapp.service

import com.josejordan.weatherapp.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getWeatherByCityAndCountry(
        @Query("q") location: String,
        @Query("units") units: String,
        @Query("appid") app_id: String
    ): Response<WeatherResponse>
}


