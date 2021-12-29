package com.yaromchikv.weatherapp.ui.weather

import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.data.repository.WeatherRepositoryImpl
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import javax.inject.Inject

class WeatherPresenter @Inject constructor(
    private val view: WeatherContract.View,
    private val getWeatherUseCase: GetWeatherUseCase
) : WeatherContract.Presenter {

    override fun onViewCreated() {
        val weather = getWeatherUseCase()
        if (weather.data != null) {
            view.showWeather(weather.data)
        } else if (weather.message != null) {
            view.showToast(weather.message)
        }
    }

    override fun onShareButtonClicked() {
        //
    }
}