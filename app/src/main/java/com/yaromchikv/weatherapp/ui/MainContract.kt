package com.yaromchikv.weatherapp.ui

interface MainContract {
    interface View {
        fun setupBottomNavigation()
    }

    interface Presenter {
        fun onCreate()
    }
}