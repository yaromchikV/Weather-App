package com.yaromchikv.weatherapp.ui.forecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentForecastBinding

class ForecastFragment : Fragment(R.layout.fragment_forecast) {

    private val binding: FragmentForecastBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}