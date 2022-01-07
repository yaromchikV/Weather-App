package com.yaromchikv.weatherapp.ui.forecast

import com.yaromchikv.weatherapp.domain.usecases.ConvertForecastToListUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetForecastUseCase
import com.yaromchikv.weatherapp.ui.common.LocationState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject
import timber.log.Timber

class ForecastPresenter @Inject constructor(
    private val view: ForecastContract.View,
    private val getForecastUseCase: GetForecastUseCase,
    private val convertForecastToListUseCase: ConvertForecastToListUseCase
) : ForecastContract.Presenter {

    override fun onViewCreated() {
        view.setupRVAdapter()
        fetchForecast()
    }

    override fun fetchForecast() {
        when (val it = view.getPosition()) {
            is LocationState.Ready -> {
                getForecastUseCase(it.data.latitude, it.data.longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        view.showProgressBar()
                    }
                    .subscribe({ forecast ->
                        val forecastWithHeaders =
                            convertForecastToListUseCase(forecast.forecastList)
                        view.updateToolbarTitle(it.data.locality)
                        view.showForecast(forecastWithHeaders)
                        Timber.d("Getting forecast continues")
                    }, { error ->
                        view.hideProgressBar()
                        view.showError(if (error !is UnknownHostException) error.localizedMessage else null)
                        Timber.d("Getting forecast error: ${error.localizedMessage}")
                    }, {
                        view.hideProgressBar()
                        Timber.d("Getting forecast complete")
                    })
            }
            is LocationState.Loading -> {
                view.showProgressBar()
            }
            is LocationState.Error -> {
                view.showError(it.message)
            }
            else -> Unit
        }
    }

    override fun onRetryButtonClicked() {
        view.hideError()
        view.reloadData()
    }
}
