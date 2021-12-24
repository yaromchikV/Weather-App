package com.yaromchikv.weatherapp.domain.model.forecast

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "cnt") val numberOfTimestamps: Int,
    @Json(name = "list") val forecastList: List<ForecastData>,
    @Json(name = "city") val city: City
)