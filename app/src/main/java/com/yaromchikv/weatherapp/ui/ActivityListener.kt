package com.yaromchikv.weatherapp.ui

import com.yaromchikv.weatherapp.ui.common.LocationState

interface ActivityListener {
    fun updateToolbarTitle(text: String)
    fun reloadData()
    fun getLocation(): LocationState
}