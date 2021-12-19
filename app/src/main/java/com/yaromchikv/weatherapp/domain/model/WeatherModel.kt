package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class WeatherModel(
    @Json(name = "clouds") val clouds: Clouds,
    @Json(name = "coord") val coordinates: Coordinates,
    @Json(name = "main") val conditions: Conditions,
    @Json(name = "name") val name: String,
    @Json(name = "sys") val location: Location,
    @Json(name = "rain") val rain: Rain,
    @Json(name = "snow") val snow: Snow,
    @Json(name = "visibility") val visibility: Int,
    @Json(name = "weather") val weatherData: List<Weather>,
    @Json(name = "wind") val wind: Wind
)