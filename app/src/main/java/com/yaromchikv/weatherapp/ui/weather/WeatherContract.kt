package com.yaromchikv.weatherapp.ui.weather

interface WeatherContract {
    interface View {
        fun showWeather(weather: String)
    }

    interface Presenter {
        fun onViewCreated()
        fun onShareButtonClicked()
    }
}