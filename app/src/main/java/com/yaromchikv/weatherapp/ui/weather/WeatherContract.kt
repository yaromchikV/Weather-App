package com.yaromchikv.weatherapp.ui.weather

import com.yaromchikv.weatherapp.domain.model.Weather

interface WeatherContract {
    interface View {
        fun showWeather(weather: Weather)
        fun showToast(message: String)
    }

    interface Presenter {
        fun fetchWeather()
        fun onViewCreated()
        fun onShareButtonClicked()
    }
}