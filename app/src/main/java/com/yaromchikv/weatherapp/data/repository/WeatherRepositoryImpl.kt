package com.yaromchikv.weatherapp.data.repository

import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.data.utils.Resource
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherRepositoryImpl(
    private val apiService: WeatherApi
) : WeatherRepository {

    private val _weatherResponse = MutableStateFlow<Resource<Weather>>(Resource.Idle())
    val weatherResponse: StateFlow<Resource<Weather>> = _weatherResponse

    override fun fetchWeather(latitude: Double, longitude: Double) {
        apiService.getWeather(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Weather?>() {
                override fun onNext(data: Weather?) {
                    _weatherResponse.value = Resource.Success(data)
                }

                override fun onError(throwable: Throwable) {
                    _weatherResponse.value = Resource.Error("Network error")
                }

                override fun onComplete() {
                    //Log.d(TAG, "Completed")
                }
            })
    }

    override fun getWeather(latitude: Double, longitude: Double): Resource<Weather> {
        fetchWeather(latitude, longitude)
        return _weatherResponse.value
    }
}