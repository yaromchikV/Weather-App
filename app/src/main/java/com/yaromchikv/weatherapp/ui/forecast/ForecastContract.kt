package com.yaromchikv.weatherapp.ui.forecast

interface ForecastContract {
    interface View {
        fun setupRVAdapter()
        fun updateToolbarTitle(text: String)
        fun showForecastList(forecastList: List<Any>)
        fun showProgressBar()
        fun hideProgressBar()
        fun showErrorImage(message: String? = null)
    }

    interface Presenter {
        fun onViewCreated()
        fun fetchForecast()
    }
}