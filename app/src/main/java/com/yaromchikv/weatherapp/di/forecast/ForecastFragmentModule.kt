package com.yaromchikv.weatherapp.di.forecast

import androidx.fragment.app.Fragment
import com.yaromchikv.weatherapp.ui.forecast.ForecastFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
object ForecastFragmentModule {

    @Provides
    fun bindFragment(fragment: Fragment): ForecastFragment {
        return fragment as ForecastFragment
    }
}
