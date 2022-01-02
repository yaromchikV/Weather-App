package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "list") val forecastList: List<ForecastData>,
    @Json(name = "city") val city: City
)

data class ForecastData(
    @Json(name = "dt_txt") val datetime: String,
    @Json(name = "main") val conditions: Conditions,
    @Json(name = "weather") val weatherData: List<WeatherData>
)

data class City(
    @Json(name = "name") val name: String
)