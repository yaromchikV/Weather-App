package com.yaromchikv.weatherapp.ui.forecast

interface ForecastContract {
    interface View {
        fun showWelcomeMessage(welcomeMessage: String)
        fun updateToolbarTitle(text: String)
    }

    interface Presenter {
        fun onViewCreated()
    }
}