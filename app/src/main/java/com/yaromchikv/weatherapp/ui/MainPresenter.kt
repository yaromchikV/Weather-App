package com.yaromchikv.weatherapp.ui

import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainContract.View
) : MainContract.Presenter {

    override fun onCreate() {
        view.setupNavigation()
    }

    override fun changeToolbarTitle(text: String) {
        view.changeToolbarTitle(text)
    }
}