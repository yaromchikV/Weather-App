package com.yaromchikv.weatherapp.domain.repository

import com.yaromchikv.weatherapp.domain.model.Forecast
import com.yaromchikv.weatherapp.domain.model.Weather
import io.reactivex.rxjava3.core.Observable

interface WeatherRepository {
    fun getWeather(latitude: Double, longitude: Double): Observable<Weather>
    fun getForecast(latitude: Double, longitude: Double): Observable<Forecast>
}
