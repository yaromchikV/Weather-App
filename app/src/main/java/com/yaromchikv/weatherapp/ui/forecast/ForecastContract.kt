package com.yaromchikv.weatherapp.ui.forecast

import com.yaromchikv.weatherapp.ui.common.LocationState

interface ForecastContract {
    interface View {
        fun setupRVAdapter()
        fun showForecast(forecastList: List<Any>)
        fun showError(message: String? = null)
        fun hideError()
        fun showProgressBar()
        fun hideProgressBar()
        fun updateLocation()
        fun updateToolbarTitle(text: String)
        fun reloadData()
        fun getLocation(): LocationState?
    }

    interface Presenter {
        fun onViewCreated()
        fun fetchForecast()
        fun onRetryButtonClicked()
    }
}
