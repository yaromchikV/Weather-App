package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(): Observable<Weather> {
        val latitude = 53.893009
        val longitude = 27.567444
        return repository.getWeather(latitude, longitude)
    }
}
