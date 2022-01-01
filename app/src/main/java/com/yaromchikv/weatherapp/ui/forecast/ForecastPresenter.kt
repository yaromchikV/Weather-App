package com.yaromchikv.weatherapp.ui.forecast

import com.yaromchikv.weatherapp.domain.model.Forecast
import com.yaromchikv.weatherapp.domain.usecases.ConvertForecastToListUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetForecastUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject
import timber.log.Timber

class ForecastPresenter @Inject constructor(
    private val view: ForecastContract.View,
    private val getForecastUseCase: GetForecastUseCase,
    private val convertForecastToListUseCase: ConvertForecastToListUseCase
) : ForecastContract.Presenter {

    override fun fetchForecast() {
        getForecastUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Forecast>() {
                override fun onNext(forecast: Forecast) {
                    view.updateToolbarTitle(forecast.city.name)
                    val forecastWithHeaders = convertForecastToListUseCase(forecast.forecastList)
                    view.showForecastList(forecastWithHeaders)

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

    override fun onViewCreated() {
        view.showProgressBar()
        fetchForecast()
    }


}