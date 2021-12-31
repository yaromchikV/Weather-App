package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.domain.model.Conditions
import com.yaromchikv.weatherapp.domain.model.ForecastData
import com.yaromchikv.weatherapp.domain.model.WeatherData
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConvertForecastToListUseCaseTest {

    val useCase = ConvertForecastToListUseCase()

    @Test
    fun test() {
        val forecastList = listOf(
            ForecastData(
                datetime = "2021-12-31 21:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
            ForecastData(
                datetime = "2022-01-01 00:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
            ForecastData(
                datetime = "2022-01-01 03:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
            ForecastData(
                datetime = "2022-01-02 09:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
        )

        val resultList = listOf(
            "TODAY",
            ForecastData(
                datetime = "2021-12-31 21:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
            "SATURDAY",
            ForecastData(
                datetime = "2022-01-01 00:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
            ForecastData(
                datetime = "2022-01-01 03:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
            "SUNDAY",
            ForecastData(
                datetime = "2022-01-02 09:00:00",
                conditions = Conditions(53, 1016, -1.5),
                weatherData = listOf(WeatherData("clear sky", "01n", 800, "Clear"))
            ),
        )

        assertEquals(resultList, useCase(forecastList))
    }
}