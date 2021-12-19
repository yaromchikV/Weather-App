package com.yaromchikv.weatherapp.di.main

import android.app.Activity
import com.yaromchikv.weatherapp.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object MainActivityModule {

    @Provides
    fun bindActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }
}