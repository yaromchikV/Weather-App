package com.yaromchikv.weatherapp.di.weather

import androidx.fragment.app.Fragment
import com.yaromchikv.weatherapp.ui.weather.WeatherContract
import com.yaromchikv.weatherapp.ui.weather.WeatherFragment
import com.yaromchikv.weatherapp.ui.weather.WeatherPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
abstract class WeatherModule {

    @Binds
    abstract fun bindFragment(fragment: WeatherFragment): WeatherContract.View

    @Binds
    abstract fun bindPresenter(presenter: WeatherPresenter): WeatherContract.Presenter

}
