package com.yaromchikv.weatherapp.di

import androidx.fragment.app.Fragment
import com.yaromchikv.weatherapp.ui.forecast.ForecastContract
import com.yaromchikv.weatherapp.ui.forecast.ForecastFragment
import com.yaromchikv.weatherapp.ui.forecast.ForecastPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class ForecastContractModule {

    @Binds
    abstract fun bindFragment(fragment: ForecastFragment): ForecastContract.View

    @Binds
    abstract fun bindPresenter(presenter: ForecastPresenter): ForecastContract.Presenter

}

@InstallIn(FragmentComponent::class)
@Module
object ForecastModule {

    @Provides
    fun bindFragment(fragment: Fragment): ForecastFragment {
        return fragment as ForecastFragment
    }
}