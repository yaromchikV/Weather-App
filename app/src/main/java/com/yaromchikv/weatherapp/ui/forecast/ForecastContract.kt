package com.yaromchikv.weatherapp.ui.forecast

import com.yaromchikv.weatherapp.domain.model.ForecastData

interface ForecastContract {
    interface View {
        fun showForecastList(forecastList: List<ForecastData>)
        fun updateToolbarTitle(text: String)
    }

    interface Presenter {
        fun fetchForecast()
        fun onViewCreated()
    }
}