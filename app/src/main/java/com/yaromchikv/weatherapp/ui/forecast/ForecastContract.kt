package com.yaromchikv.weatherapp.ui.forecast

import com.yaromchikv.weatherapp.ui.common.LocationState

interface ForecastContract {
    interface View {
        fun setupRVAdapter()
        fun updateToolbarTitle(text: String)
        fun showForecast(forecastList: List<Any>)
        fun showError(message: String? = null)
        fun showProgressBar()
        fun hideProgressBar()
        fun updatePosition()
        fun getPosition(): LocationState?
    }

    interface Presenter {
        fun onViewCreated()
        fun fetchForecast()
    }
}