package com.yaromchikv.weatherapp.domain.model.weather

import com.squareup.moshi.Json

data class Snow(
    @Json(name = "3h") val volume: Double
)