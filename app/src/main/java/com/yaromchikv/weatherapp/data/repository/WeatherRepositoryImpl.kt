package com.yaromchikv.weatherapp.data.repository

import com.yaromchikv.weatherapp.data.Resource
import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.domain.model.WeatherModel
import com.yaromchikv.weatherapp.domain.model.forecast.ForecastModel
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(latitude: Double, longitude: Double): Resource<WeatherModel> {
        return try {
            val response = api.getWeather(latitude = latitude, longitude = longitude)
            val result = response.body()
            if (response.isSuccessful && result != null)
                Resource.Success(result)
            else Resource.Error(response.message())
        } catch (e: Exception) {
            Resource.Error("Check your connection")
        }
    }

    override suspend fun getForecast(latitude: Double, longitude: Double): Resource<ForecastModel> {
        return try {
            val response = api.getForecast(latitude = latitude, longitude = longitude)
            val result = response.body()
            if (response.isSuccessful && result != null)
                Resource.Success(result)
            else Resource.Error(response.message())
        } catch (e: Exception) {
            Resource.Error("Check your connection")
        }
    }
}