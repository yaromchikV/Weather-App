package com.yaromchikv.weatherapp.domain.repository

import com.yaromchikv.weatherapp.data.utils.Resource
import com.yaromchikv.weatherapp.domain.model.weather.Weather
import com.yaromchikv.weatherapp.domain.model.forecast.Forecast

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Resource<Weather>
    suspend fun getForecast(latitude: Double, longitude: Double): Resource<Forecast>
}