package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class Conditions(
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "temp") val temperature: Int,
)