package com.yaromchikv.weatherapp.ui.forecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.fragment_forecast), ForecastContract.View {

    private val binding: FragmentForecastBinding by viewBinding()

    @Inject
    lateinit var presenter: ForecastContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun showWelcomeMessage(welcomeMessage: String) {
        //binding.welcomeMessageTitle.text = welcomeMessage.title
    }
}