package com.yaromchikv.weatherapp.ui.forecast

interface ForecastContract {
    interface View {
        fun showForecastList(forecastList: List<Any>)
        fun updateToolbarTitle(text: String)
    }

    interface Presenter {
        fun fetchForecast()
        fun onViewCreated()
    }
}