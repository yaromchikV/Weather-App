package com.yaromchikv.weatherapp.ui

import com.yaromchikv.weatherapp.ui.common.LocationState
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainContract.View
) : MainContract.Presenter {

    private var location: LocationState = LocationState.Loading

    override fun onCreate() {
        view.setupNavigation()
    }

    override fun onStart() {
        view.determineLocation()
    }

    override fun changeToolbarTitle(text: String) {
        view.changeToolbarTitle(text)
    }

    override fun setLocation(location: LocationState) {
        this.location = location
    }

    override fun getLocation() = location

    override fun onUpdateLocation() {
        view.updateLocation()
    }

    override fun onGpsDisabled() {
        view.showGpsDialog()
    }

    override fun onPermissionDenied() {
        view.showPermissionDeniedDialog()
    }
}