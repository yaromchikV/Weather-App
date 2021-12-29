package com.yaromchikv.weatherapp.domain.repository

import com.yaromchikv.weatherapp.data.utils.Resource
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.model.Forecast
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepository {
    fun fetchWeather(latitude: Double, longitude: Double)
    fun getWeather(latitude: Double, longitude: Double): Resource<Weather>
}