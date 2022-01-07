package com.yaromchikv.weatherapp.di.forecast

import com.yaromchikv.weatherapp.ui.forecast.ForecastContract
import com.yaromchikv.weatherapp.ui.forecast.ForecastFragment
import com.yaromchikv.weatherapp.ui.forecast.ForecastPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class ForecastModule {

    @Binds
    abstract fun bindFragment(fragment: ForecastFragment): ForecastContract.View

    @Binds
    abstract fun bindPresenter(presenter: ForecastPresenter): ForecastContract.Presenter
}
