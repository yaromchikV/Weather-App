package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.data.Resource
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double) {
        when(val result = repository.getWeather(latitude, longitude)) {
            is Resource.Success -> {
                result.data
            }
            is Resource.Error -> {
                result.message
            }
        }
    }
}
