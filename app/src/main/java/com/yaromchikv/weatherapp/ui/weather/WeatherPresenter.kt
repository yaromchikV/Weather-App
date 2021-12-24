package com.yaromchikv.weatherapp.ui.weather

import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import javax.inject.Inject

class WeatherPresenter @Inject constructor(
    private val view: WeatherContract.View,
    private val getWeatherUseCase: GetWeatherUseCase
) : WeatherContract.Presenter {

    override fun onViewCreated() {
        val weather = "message"
        view.showWeather(weather)
    }

    override fun onShareButtonClicked() {
        //
    }
}