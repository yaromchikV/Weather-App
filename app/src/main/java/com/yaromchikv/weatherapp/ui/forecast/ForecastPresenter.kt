package com.yaromchikv.weatherapp.ui.forecast

import javax.inject.Inject

class ForecastPresenter @Inject constructor(
    private val view: ForecastContract.View
): ForecastContract.Presenter {

    override fun onViewCreated() {
        val welcomeMessage = "message"
        view.showWelcomeMessage(welcomeMessage)
    }
}