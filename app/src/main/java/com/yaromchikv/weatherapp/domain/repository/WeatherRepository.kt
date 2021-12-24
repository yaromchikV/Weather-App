package com.yaromchikv.weatherapp.domain.repository

import com.yaromchikv.weatherapp.data.Resource
import com.yaromchikv.weatherapp.domain.model.WeatherModel
import com.yaromchikv.weatherapp.domain.model.forecast.ForecastModel

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Resource<WeatherModel>
    suspend fun getForecast(latitude: Double, longitude: Double): Resource<ForecastModel>
}