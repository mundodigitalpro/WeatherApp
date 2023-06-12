package com.josejordan.weatherapp.di

import com.josejordan.weatherapp.repository.WeatherRepository
import com.josejordan.weatherapp.repository.WeatherRepositoryImpl
import com.josejordan.weatherapp.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherServiceModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherService): WeatherRepository {
        return WeatherRepositoryImpl(service)
    }


    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}
