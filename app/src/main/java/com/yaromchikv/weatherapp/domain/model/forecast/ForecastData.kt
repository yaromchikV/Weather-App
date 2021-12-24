package com.yaromchikv.weatherapp.domain.model.forecast

import com.squareup.moshi.Json
import com.yaromchikv.weatherapp.domain.model.weather.Conditions
import com.yaromchikv.weatherapp.domain.model.weather.WeatherData

data class ForecastData(
    @Json(name = "dt_txt") val datetime: String,
    @Json(name = "main") val conditions: Conditions,
    @Json(name = "weather") val weatherData: List<WeatherData>
)