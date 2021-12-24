package com.yaromchikv.weatherapp.domain.model.weather

import com.squareup.moshi.Json

data class Location(
    @Json(name = "country") val country: String
)