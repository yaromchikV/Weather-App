package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.domain.model.Forecast
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetForecastUseCase(private val repository: WeatherRepository) {
    operator fun invoke(): Observable<Forecast> {
        val latitude = 53.893009
        val longitude = 27.567444
        return repository.getForecast(latitude, longitude)
    }
}