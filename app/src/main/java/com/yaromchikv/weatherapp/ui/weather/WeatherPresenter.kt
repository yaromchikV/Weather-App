package com.yaromchikv.weatherapp.ui.weather

import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import timber.log.Timber

class WeatherPresenter @Inject constructor(
    private val view: WeatherContract.View,
    private val getWeatherUseCase: GetWeatherUseCase
) : WeatherContract.Presenter {

    override fun fetchWeather() {
        getWeatherUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Weather>() {
                override fun onNext(weather: Weather) {
                    view.showWeather(weather)
                    Timber.d("Getting weather continues")
                }

                override fun onError(throwable: Throwable) {
                    view.hideProgressBar()
                    view.showErrorImage()
                    Timber.d("Getting weather error: ${throwable.localizedMessage}")
                }

                override fun onComplete() {
                    view.hideProgressBar()
                    Timber.d("Getting weather complete")
                }
            })
    }

    override fun onViewCreated() {
        view.showProgressBar()
        fetchWeather()
    }

    override fun onShareButtonClicked() {
        //
    }
}