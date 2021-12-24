package com.yaromchikv.weatherapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.data.repository.WeatherRepositoryImpl
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_CURRENCY_URL = "https://api.openweathermap.org/"

    private val moshi = MoshiConverterFactory.create(
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    )

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi = Retrofit.Builder()
        .addConverterFactory(moshi)
        .baseUrl(BASE_CURRENCY_URL)
        .build()
        .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository = WeatherRepositoryImpl(api)

//    @Provides
//    @Singleton
//    fun provideConvertCurrencyUseCase(repository: ConverterRepository): ConvertCurrencyUseCase =
//        ConvertCurrencyUseCase(repository)
}
