package com.yaromchikv.weatherapp.di

import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import com.yaromchikv.weatherapp.domain.usecases.ConvertForecastToListUseCase
import com.yaromchikv.weatherapp.domain.usecases.GenerateMessageForSharingUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetForecastUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(repository: WeatherRepository): GetWeatherUseCase =
        GetWeatherUseCase(repository)

    @Provides
    @Singleton
    fun provideGetForecastUseCase(repository: WeatherRepository): GetForecastUseCase =
        GetForecastUseCase(repository)

    @Provides
    @Singleton
    fun provideConvertForecastToListUseCase(): ConvertForecastToListUseCase =
        ConvertForecastToListUseCase()

    @Provides
    @Singleton
    fun provideGenerateMessageForSharingUseCase(): GenerateMessageForSharingUseCase =
        GenerateMessageForSharingUseCase()
}