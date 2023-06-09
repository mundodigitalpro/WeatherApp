package com.josejordan.weatherapp

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Coord(val lon: Float, val lat: Float)
data class Weather(val id: Int, val main: String, val description: String, val icon: String)
data class Main(val temp: Float, val feels_like: Float, val temp_min: Float, val temp_max: Float, val pressure: Int, val humidity: Int)
data class Wind(val speed: Float, val deg: Int, val gust: Float)
data class Clouds(val all: Int)
data class Sys(val type: Int, val id: Int, val country: String, val sunrise: Int, val sunset: Int)
