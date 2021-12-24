package com.yaromchikv.weatherapp.ui.weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather), WeatherContract.View {

    private val binding: FragmentWeatherBinding by viewBinding()

    @Inject
    lateinit var presenter: WeatherContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun showWelcomeMessage(welcomeMessage: String) {
        //binding.welcomeMessageTitle.text = welcomeMessage.title
    }
}