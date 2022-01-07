package com.yaromchikv.weatherapp.ui.common

data class LocationModel(
    val latitude: Double,
    val longitude: Double,
    val locality: String,
    val country: String
)

sealed class LocationState {
    data class Ready(val data: LocationModel) : LocationState()
    object Loading : LocationState()
    data class Error(val message: String?) : LocationState()
}
