package com.yaromchikv.weatherapp.ui.forecast

interface ForecastContract {
    interface View {
        fun showWelcomeMessage(welcomeMessage: String)
    }

    interface Presenter {
        fun onViewCreated()
    }
}