package com.yaromchikv.weatherapp.domain.usecases

import com.yaromchikv.weatherapp.domain.model.ForecastData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ConvertForecastToListUseCase {
    operator fun invoke(_source: List<ForecastData>): List<Any> {
        val source = _source.reversed()
        val result = mutableListOf<Any>()
        for (i in source.indices) {
            result.add(source[i])
            if (i != source.lastIndex) {
                val currentDate = dateTimeParse(source[i].datetime)
                val nextDate = dateTimeParse(source[i + 1].datetime)
                if (currentDate != nextDate) {
                    result.add(currentDate.dayOfWeek.name)
                }
            } else {
                result.add("TODAY")
                break
            }
        }
        return result.reversed()
    }

    private fun dateTimeParse(datetime: String): LocalDate =
        LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}