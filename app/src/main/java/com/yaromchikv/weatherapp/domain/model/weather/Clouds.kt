package com.yaromchikv.weatherapp.domain.model.weather

import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all") val cloudiness: Int
)