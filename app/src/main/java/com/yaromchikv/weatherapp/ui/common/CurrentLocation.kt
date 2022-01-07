package com.yaromchikv.weatherapp.ui.common

data class CurrentLocation(
    val latitude: Double,
    val longitude: Double,
    val locality: String,
    val country: String
)

sealed class LocationState {
    data class Ready(val data: CurrentLocation) : LocationState()
    object Loading : LocationState()
    data class Error(val message: String?) : LocationState()
}
