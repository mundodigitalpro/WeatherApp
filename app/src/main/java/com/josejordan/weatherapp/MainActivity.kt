package com.josejordan.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var weatherInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherInfoTextView = findViewById(R.id.weatherInfoTextView)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(
            lat = "35",
            lon = "139",
            units = "metric",
            app_id = "your_api_key"
        )


        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    Log.d("MainActivity", "Response: $weatherResponse")

                    val stringBuilder = "City: " +
                            weatherResponse.name +
                            "\nCountry: " +
                            weatherResponse.sys.country +
                            "\nDescription: " +
                            weatherResponse.weather[0].description +
                            "\nTemperature: " +
                            String.format("%.2f", weatherResponse.main.temp) + "°C"

                    weatherInfoTextView.text = stringBuilder
                }
            }


            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("MainActivity", "Error: ${t.message}")
            }
        })
    }
}
