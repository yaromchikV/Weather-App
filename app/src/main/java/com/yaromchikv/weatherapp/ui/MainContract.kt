package com.yaromchikv.weatherapp.ui

import com.yaromchikv.weatherapp.ui.common.LocationState

interface MainContract {
    interface View {
        fun setupNavigation()
        fun changeToolbarTitle(text: String)
        fun determineLocation()
        fun updateLocation()
        fun showGpsDialog()
        fun showPermissionDeniedDialog()
    }

    interface Presenter {
        fun onCreate()
        fun readyToLoad()
        fun changeToolbarTitle(text: String)
        fun setLocation(location: LocationState)
        fun getLocation(): LocationState
        fun onUpdateLocation()
        fun onGpsDisabled()
        fun onPermissionDenied()
    }
}