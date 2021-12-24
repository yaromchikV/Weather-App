package com.yaromchikv.weatherapp.domain.model.forecast

import com.squareup.moshi.Json

data class City(
    @Json(name = "name") val name: String
)