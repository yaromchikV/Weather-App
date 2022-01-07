package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(latitude: Double, longitude: Double): Observable<Weather> {
        return repository.getWeather(latitude, longitude)
    }
}
