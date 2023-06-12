package com.josejordan.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("data/2.5/weather")
    fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") app_id: String
    ): Call<WeatherResponse>

    @GET("data/2.5/weather")
    fun getWeatherByCityAndCountry(
        @Query("q") location: String,
        @Query("units") units: String,
        @Query("appid") app_id: String
    ): Call<WeatherResponse>
}
