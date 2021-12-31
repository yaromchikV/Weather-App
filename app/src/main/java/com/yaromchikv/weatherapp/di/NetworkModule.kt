package com.yaromchikv.weatherapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yaromchikv.weatherapp.data.api.WeatherApi
import com.yaromchikv.weatherapp.data.repository.WeatherRepositoryImpl
import com.yaromchikv.weatherapp.domain.repository.WeatherRepository
import com.yaromchikv.weatherapp.domain.usecases.GetForecastUseCase
import com.yaromchikv.weatherapp.domain.usecases.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_CURRENCY_URL = "https://api.openweathermap.org/"

    private val moshiBuilder = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi = Retrofit.Builder()
        .baseUrl(BASE_CURRENCY_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
        .build()
        .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository = WeatherRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(repository: WeatherRepository): GetWeatherUseCase =
        GetWeatherUseCase(repository)

    @Provides
    @Singleton
    fun provideGetForecastUseCase(repository: WeatherRepository): GetForecastUseCase =
        GetForecastUseCase(repository)
}
