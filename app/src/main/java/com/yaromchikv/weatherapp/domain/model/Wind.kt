package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class Wind(
    @Json(name = "speed") val speed: Double
)