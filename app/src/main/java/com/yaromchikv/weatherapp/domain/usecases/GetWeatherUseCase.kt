package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.data.utils.Resource
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(): Resource<Weather> {
        val latitude = 53.893009
        val longitude = 27.567444
        return repository.getWeather(latitude, longitude)
    }
}
