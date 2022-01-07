package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class Weather(
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "main") val conditions: Conditions,
    @Json(name = "clouds") val clouds: Clouds,
    @Json(name = "rain") val rain: Rain?,
    @Json(name = "snow") val snow: Snow?,
    @Json(name = "wind") val wind: Wind
)

data class WeatherData(
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String
)

data class Conditions(
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "temp") val temperature: Double,
)

data class Clouds(
    @Json(name = "all") val cloudiness: Int
)

data class Rain(
    @Json(name = "1h") val volume1h: Double?,
    @Json(name = "3h") val volume3h: Double?
) {
    val volume: Double get() = volume1h ?: volume3h ?: 0.0
}

data class Snow(
    @Json(name = "1h") val volume1h: Double?,
    @Json(name = "3h") val volume3h: Double?
) {
    val volume: Double get() = volume1h ?: volume3h ?: 0.0
}

data class Wind(
    @Json(name = "speed") val speed: Double,
    @Json(name = "deg") val degrees: Int
)