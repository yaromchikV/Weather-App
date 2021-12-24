package com.yaromchikv.weatherapp.domain.model.weather

import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "lat") val latitude: Double,
    @Json(name = "lon") val longitude: Double
)