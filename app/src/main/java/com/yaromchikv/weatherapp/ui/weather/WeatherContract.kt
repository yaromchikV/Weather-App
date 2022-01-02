package com.yaromchikv.weatherapp.ui.weather

import com.yaromchikv.weatherapp.domain.model.Weather

interface WeatherContract {
    interface View {
        fun showWeather(weather: Weather)
        fun showProgressBar()
        fun hideProgressBar()
        fun showErrorImage(message: String? = null)
        fun shareWeather(subject: String, text: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun fetchWeather()
        fun onShareButtonClicked()
    }
}