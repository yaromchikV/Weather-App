package com.yaromchikv.weatherapp.domain.model.forecast

import com.squareup.moshi.Json

data class ForecastModel(
    @Json(name = "cnt") val numberOfTimestamps: Int,
    @Json(name = "list") val forecastList: List<Forecast>,
)