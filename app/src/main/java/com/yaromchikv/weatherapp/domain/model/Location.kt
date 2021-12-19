package com.yaromchikv.weatherapp.domain.model

import com.squareup.moshi.Json

data class Location(
    @Json(name = "country") val country: String
)