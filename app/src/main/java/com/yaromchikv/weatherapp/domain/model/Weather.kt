package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class Weather(
    @Json(name = "weather") val weatherData: List<WeatherData>,
    @Json(name = "main") val conditions: Conditions,
    @Json(name = "clouds") val clouds: Clouds,
    @Json(name = "rain") val rain: Rain?,
    @Json(name = "snow") val snow: Snow?,
    @Json(name = "wind") val wind: Wind,
    @Json(name = "visibility") val visibility: Int,
    @Json(name = "name") val city: String,
    @Json(name = "sys") val location: Location,
    @Json(name = "coord") val coordinates: Coordinates,
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
    @Json(name = "temp") val temperature: Int,
)

data class Clouds(@Json(name = "all") val cloudiness: Int)
data class Rain(@Json(name = "3h") val volume: Double)
data class Snow(@Json(name = "3h") val volume: Double)
data class Wind(@Json(name = "speed") val speed: Double)

data class Location(@Json(name = "country") val country: String)

data class Coordinates(
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lon") val longitude: Double
)