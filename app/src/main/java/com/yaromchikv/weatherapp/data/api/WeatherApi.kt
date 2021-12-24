package com.yaromchikv.weatherapp.data.api

import com.yaromchikv.weatherapp.common.Constants
import com.yaromchikv.weatherapp.domain.model.weather.Weather
import com.yaromchikv.weatherapp.domain.model.forecast.Forecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Response<Weather>

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = Constants.API_KEY
    ): Response<Forecast>

}