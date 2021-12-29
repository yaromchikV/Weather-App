package com.yaromchikv.weatherapp.data.api

import com.yaromchikv.weatherapp.domain.model.Forecast
import com.yaromchikv.weatherapp.domain.model.Weather
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    // http://api.openweathermap.org/data/2.5/weather?lat=53.893009&lon=27.567444&units=metric&appid=8d4d5a6106195ad1d297c4a84a2aae38
    @GET("data/2.5/weather?appid=8d4d5a6106195ad1d297c4a84a2aae38")
    fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric"
        //@Query("appid") apiKey: String = Constants.API_KEY
    ): Observable<Weather>

    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        //@Query("appid") apiKey: String = Constants.API_KEY
    ): Observable<Forecast>

}