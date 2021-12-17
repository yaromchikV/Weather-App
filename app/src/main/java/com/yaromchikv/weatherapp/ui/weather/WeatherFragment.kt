package com.yaromchikv.weatherapp.ui.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val binding: FragmentWeatherBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}