package com.yaromchikv.weatherapp.ui.common

import com.yaromchikv.weatherapp.R

object Utils {

    fun getIcon(code: String) = when (code) {
        "01d" -> R.drawable.icon_01d
        "01n" -> R.drawable.icon_01n
        "02d" -> R.drawable.icon_02d
        "02n" -> R.drawable.icon_02n
        "03d" -> R.drawable.icon_03dn
        "03n" -> R.drawable.icon_03dn
        "04d" -> R.drawable.icon_04dn
        "04n" -> R.drawable.icon_04dn
        "09d" -> R.drawable.icon_09d
        "09n" -> R.drawable.icon_09n
        "10d" -> R.drawable.icon_10d
        "10n" -> R.drawable.icon_10n
        "11d" -> R.drawable.icon_11d
        "11n" -> R.drawable.icon_11n
        "13d" -> R.drawable.icon_13dn
        "13n" -> R.drawable.icon_13dn
        "50d" -> R.drawable.icon_50dn
        "50n" -> R.drawable.icon_50dn
        else -> R.drawable.ic_broken_image
    }

    fun getDirection(angleValue: Int): String {
        val directions = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val angle = angleValue % 360
        val index = (if (angle < 0) angle + 360 else angle / 45) % 8

        return directions[index]
    }

}