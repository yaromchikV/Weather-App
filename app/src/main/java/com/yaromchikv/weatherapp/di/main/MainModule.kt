package com.yaromchikv.weatherapp.di.main

import com.yaromchikv.weatherapp.ui.MainActivity
import com.yaromchikv.weatherapp.ui.MainContract
import com.yaromchikv.weatherapp.ui.MainPresenter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: MainActivity): MainContract.View

    @Binds
    abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter
}
