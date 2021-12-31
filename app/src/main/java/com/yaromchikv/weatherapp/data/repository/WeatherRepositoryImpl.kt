package com.yaromchikv.weatherapp.data.repository

import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.domain.model.Forecast
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.core.Observable

class WeatherRepositoryImpl(
    private val apiService: WeatherApi
) : WeatherRepository {

    override fun getWeather(latitude: Double, longitude: Double): Observable<Weather> {
        return apiService.getWeather(latitude, longitude)
    }

    override fun getForecast(latitude: Double, longitude: Double): Observable<Forecast> {
        return apiService.getForecast(latitude, longitude)
    }

}