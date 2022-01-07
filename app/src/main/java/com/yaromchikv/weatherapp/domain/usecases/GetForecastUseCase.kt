package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.domain.model.Forecast
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class GetForecastUseCase(private val repository: WeatherRepository) {
    operator fun invoke(latitude: Double, longitude: Double): Observable<Forecast> {
        return repository.getForecast(latitude, longitude)
    }
}
