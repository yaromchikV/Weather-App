package com.yaromchikv.weatherapp.ui.forecast

interface ForecastContract {
    interface View {
        fun showForecastList(forecastList: List<Any>)
        fun updateToolbarTitle(text: String)
        fun showProgressBar()
        fun hideProgressBar()
        fun showErrorImage(message: String? = null)
    }

    interface Presenter {
        fun fetchForecast()
        fun onViewCreated()
    }
}