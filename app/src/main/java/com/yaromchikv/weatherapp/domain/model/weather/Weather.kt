package com.yaromchikv.weatherapp.domain.model.weather

import com.squareup.moshi.Json

data class Weather(
    @Json(name = "clouds") val clouds: Clouds,
    @Json(name = "coord") val coordinates: Coordinates,
    @Json(name = "main") val conditions: Conditions,
    @Json(name = "name") val city: String,
    @Json(name = "sys") val location: Location,
    @Json(name = "rain") val rain: Rain,
    @Json(name = "snow") val snow: Snow,
    @Json(name = "visibility") val visibility: Int,
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "wind") val wind: Wind
)