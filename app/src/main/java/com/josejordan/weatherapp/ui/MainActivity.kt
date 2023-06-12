package com.josejordan.weatherapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.josejordan.weatherapp.R
import com.josejordan.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var weatherInfoTextView: TextView
    private lateinit var cityEditText: EditText
    private lateinit var countryEditText: EditText
    private lateinit var searchButton: Button

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherInfoTextView = findViewById(R.id.weatherInfoTextView)
        cityEditText = findViewById(R.id.cityEditText)
        countryEditText = findViewById(R.id.countryEditText)
        searchButton = findViewById(R.id.searchButton)

        searchButton.setOnClickListener {
            val city = cityEditText.text.toString()
            val country = countryEditText.text.toString()

            viewModel.getWeather(city, country)

            viewModel.weather.observe(this) { weatherResponse ->
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
    }

}




