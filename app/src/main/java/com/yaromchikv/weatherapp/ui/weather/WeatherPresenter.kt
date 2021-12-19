package com.yaromchikv.weatherapp.ui.weather

import javax.inject.Inject

class WeatherPresenter @Inject constructor(
    private val view: WeatherContract.View
): WeatherContract.Presenter {

    override fun onViewCreated() {
        val welcomeMessage = "message"
        view.showWelcomeMessage(welcomeMessage)
    }
}