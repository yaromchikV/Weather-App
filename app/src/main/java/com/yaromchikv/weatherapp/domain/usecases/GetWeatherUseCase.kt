package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.data.utils.Resource
import com.yaromchikv.weatherapp.domain.model.weather.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Resource<Weather> {
        return repository.getWeather(latitude, longitude)
    }
}
