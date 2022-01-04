package com.yaromchikv.weatherapp.ui

interface MainContract {
    interface View {
        fun setupNavigation()
        fun changeToolbarTitle(text: String)
    }

    interface Presenter {
        fun onCreate()
        fun changeToolbarTitle(text: String)
    }
}