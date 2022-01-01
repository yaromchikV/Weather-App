package com.yaromchikv.weatherapp.common

object Utils {
    fun getDirection(angleValue: Int): String {
        val directions = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")
        val angle = angleValue % 360
        val index = (if (angle < 0) angle + 360 else angle / 45) % 8

        return directions[index]
    }
}