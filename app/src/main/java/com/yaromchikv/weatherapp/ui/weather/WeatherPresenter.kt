package com.yaromchikv.weatherapp.ui.weather

import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.usecases.GenerateMessageForSharingUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

class WeatherPresenter @Inject constructor(
    private val view: WeatherContract.View,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val generateMessageForSharingUseCase: GenerateMessageForSharingUseCase
) : WeatherContract.Presenter {

    private val currentWeather = MutableStateFlow<Weather?>(null)

    override fun onViewCreated() {
        view.showProgressBar()
        fetchWeather()
    }

    override fun fetchWeather() {
        getWeatherUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Weather>() {
                override fun onNext(weather: Weather) {
                    currentWeather.value = weather
                    view.showWeather(weather)

                    Timber.d("Getting weather continues")
                }

                override fun onError(throwable: Throwable) {
                    view.hideProgressBar()

                    if (throwable is UnknownHostException)
                        view.showErrorImage()
                    else
                        view.showErrorImage(throwable.localizedMessage)

                    Timber.d("Getting weather error: ${throwable.localizedMessage}")
                }

                override fun onComplete() {
                    view.hideProgressBar()

                    Timber.d("Getting weather complete")
                }
            })
    }

    override fun onShareButtonClicked() {
        if (currentWeather.value != null) {
            val message = generateMessageForSharingUseCase(currentWeather.value!!)
            view.shareWeather(message.first, message.second)
        }
    }
}