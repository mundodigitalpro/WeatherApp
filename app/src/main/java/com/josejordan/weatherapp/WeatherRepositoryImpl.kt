package com.josejordan.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
): WeatherRepository {

    private val _weather = MutableLiveData<WeatherResponse>()

/*    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()*/

    //private val service = retrofit.create(WeatherService::class.java)

    override fun getWeather(city: String, country: String): LiveData<WeatherResponse> {

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
                    _weather.postValue(weatherResponse)
                }
            }


            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle failure
            }
        })

        return _weather
    }
}
