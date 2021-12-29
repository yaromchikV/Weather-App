package com.yaromchikv.weatherapp.data.repository

import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.data.utils.Resource
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

class WeatherRepositoryImpl(
    private val apiService: WeatherApi
) : WeatherRepository {

    private val _weatherResponse = MutableStateFlow<Resource<Weather>>(Resource.Idle())

    override fun fetchWeather(latitude: Double, longitude: Double) {
        apiService.getWeather(latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Weather>() {
                override fun onNext(data: Weather) {
                    _weatherResponse.value = Resource.Success(data)
                    Timber.i("Getting weather continues")
                }

                override fun onError(throwable: Throwable) {
                    _weatherResponse.value = Resource.Error("Network error")
                    Timber.i("Getting weather error: ${throwable.localizedMessage}")
                }

                override fun onComplete() {
                    Timber.i("Getting weather complete")
                }
            })
    }

    override fun getWeather(latitude: Double, longitude: Double): Resource<Weather> {
        fetchWeather(latitude, longitude)
        return _weatherResponse.value
    }
}