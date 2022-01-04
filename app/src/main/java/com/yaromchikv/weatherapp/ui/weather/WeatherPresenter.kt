package com.yaromchikv.weatherapp.ui.weather

import android.content.Intent
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.usecases.GenerateMessageForSharingUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject
import timber.log.Timber

class WeatherPresenter @Inject constructor(
    private val view: WeatherContract.View,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val generateMessageForSharingUseCase: GenerateMessageForSharingUseCase
) : WeatherContract.Presenter {

    private var currentWeather: Weather? = null

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
                    currentWeather = weather
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
        if (currentWeather != null) {
            val message = generateMessageForSharingUseCase(currentWeather ?: return)

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT, message.first)
                putExtra(Intent.EXTRA_TEXT, message.second)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)

            view.openShareActivity(shareIntent)
        }
    }
}