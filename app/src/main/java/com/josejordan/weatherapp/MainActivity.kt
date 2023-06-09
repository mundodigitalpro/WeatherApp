package com.josejordan.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var weatherInfoTextView: TextView
    private lateinit var cityEditText: EditText
    private lateinit var countryEditText: EditText
    private lateinit var searchButton: Button

    // Get a reference to the ViewModel
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherInfoTextView = findViewById(R.id.weatherInfoTextView)
        cityEditText = findViewById(R.id.cityEditText)
        countryEditText = findViewById(R.id.countryEditText)
        searchButton = findViewById(R.id.searchButton)

        // Observe the weather LiveData
        viewModel.weather.observe(this) { weather ->
            weatherInfoTextView.text = weather
        }

        searchButton.setOnClickListener {
            val city = cityEditText.text.toString()
            val country = countryEditText.text.toString()

            viewModel.fetchWeather(city, country)
        }
    }
}



