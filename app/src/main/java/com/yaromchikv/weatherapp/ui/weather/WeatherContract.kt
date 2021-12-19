package com.yaromchikv.weatherapp.ui.weather

interface WeatherContract {
    interface View {
        fun showWelcomeMessage(welcomeMessage: String)
    }

    interface Presenter {
        fun onViewCreated()
    }
}