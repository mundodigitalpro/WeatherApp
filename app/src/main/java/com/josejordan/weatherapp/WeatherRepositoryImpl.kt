package com.josejordan.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepositoryImpl : WeatherRepository {
    private val _weather = MutableLiveData<String>()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    override fun getWeather(city: String, country: String): LiveData<String> {
        val call = service.getWeatherByCityAndCountry(
            location = "$city,$country",
            units = "metric",
            app_id = "854adb87b1ddd246adf542c47f3eeca0"
        )

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    val stringBuilder = "City: " +
                            weatherResponse.name +
                            "\nCountry: " +
                            weatherResponse.sys.country +
                            "\nDescription: " +
                            weatherResponse.weather[0].description +
                            "\nTemperature: " +
                            String.format("%.2f", weatherResponse.main.temp) + "°C"

                    _weather.postValue(stringBuilder)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure
            }
        })

        return _weather
    }
}