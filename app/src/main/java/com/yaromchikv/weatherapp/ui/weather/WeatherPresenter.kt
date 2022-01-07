package com.yaromchikv.weatherapp.ui.weather

import android.content.Intent
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.domain.usecases.GenerateMessageForSharingUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import com.yaromchikv.weatherapp.ui.common.CurrentLocation
import com.yaromchikv.weatherapp.ui.common.LocationState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
    private var currentLocation: CurrentLocation? = null

    override fun onViewCreated() {
        fetchWeather()
    }

    override fun fetchWeather() {
        when (val it = view.getPosition()) {
            is LocationState.Ready -> {
                currentLocation = it.data
                getWeatherUseCase(it.data.latitude, it.data.longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        view.showProgressBar()
                    }
                    .subscribe({ weather ->
                        currentWeather = weather
                        view.showWeather(weather, it.data.locality, it.data.country)
                        Timber.d("Getting weather continues")
                    }, { error ->
                        view.showError(if (error !is UnknownHostException) error.localizedMessage else null)
                        Timber.d("Getting weather error: ${error.localizedMessage}")
                    }, {
                        view.hideProgressBar()
                        Timber.d("Getting weather complete")
                    })
            }
            is LocationState.Loading -> {
                view.showProgressBar()
            }
            is LocationState.Error -> {
                currentLocation = null
                view.showError(it.message)
            }
            else -> Unit
        }
    }

    override fun onShareButtonClicked() {
        if (currentWeather != null) {
            val message = generateMessageForSharingUseCase(
                currentWeather ?: return,
                (currentLocation?.locality ?: return) to (currentLocation?.country ?: return)
            )

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

    override fun onRetryButtonClicked() {
        view.hideError()
        view.reloadData()
    }
}