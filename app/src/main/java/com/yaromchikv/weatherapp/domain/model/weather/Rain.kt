package com.yaromchikv.weatherapp.domain.model.weather

import com.squareup.moshi.Json

data class Rain(
    @Json(name = "3h") val volume: Double
)