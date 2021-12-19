package com.yaromchikv.weatherapp.di.weather

import androidx.fragment.app.Fragment
import com.yaromchikv.weatherapp.ui.weather.WeatherFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object WeatherFragmentModule {

    @Provides
    fun bindFragment(fragment: Fragment): WeatherFragment {
        return fragment as WeatherFragment
    }
}