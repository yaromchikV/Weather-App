package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.common.Utils.getDirection
import com.yaromchikv.weatherapp.domain.model.Weather
import java.text.DecimalFormat
import org.apache.commons.lang3.text.WordUtils

class GenerateMessageForSharingUseCase {
    operator fun invoke(weather: Weather, location: Pair<String, String>): Pair<String, String> {
        val subject = "Current weather: ${location.first}"
        val text = "Location: ${location.first}, ${location.second}.\n" +
                "Weather: ${WordUtils.capitalizeFully(weather.weatherData[0].description)}.\n" +
                "Temperature: ${format(weather.conditions.temperature)}°C.\n" +
                "Humidity: ${weather.conditions.humidity}%.\n" +
                "Pressure: ${weather.conditions.pressure} hPa.\n" +
                "Cloudiness: ${weather.clouds.cloudiness}%.\n" +
                "Rain volume: ${format(weather.rain?.volume)} mm.\n" +
                "Snow volume: ${format(weather.snow?.volume)} mm.\n" +
                "Wind speed: ${format(weather.wind.speed)} m/s.\n" +
                "Wind direction: ${getDirection(weather.wind.degrees)}."
        return subject to text
    }

    private fun format(number: Double?) =
        if (number != null) DecimalFormat("#.##").format(number) else "0"
}
