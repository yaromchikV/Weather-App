package com.yaromchikv.weatherapp.ui.weather

import android.content.Intent
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.ui.common.LocationState

interface WeatherContract {
    interface View {
        fun showWeather(weather: Weather, locality: String, country: String)
        fun showError(message: String? = null)
        fun hideError()
        fun showProgressBar()
        fun hideProgressBar()
        fun openShareActivity(intent: Intent)
        fun updateLocation()
        fun updateToolbarTitle()
        fun reloadData()
        fun getLocation(): LocationState?
    }

    interface Presenter {
        fun onViewCreated()
        fun fetchWeather()
        fun onShareButtonClicked()
        fun onRetryButtonClicked()
    }
}