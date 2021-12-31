package com.yaromchikv.weatherapp.ui.forecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentForecastBinding
import com.yaromchikv.weatherapp.domain.model.ForecastData
import com.yaromchikv.weatherapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment(R.layout.fragment_forecast), ForecastContract.View {

    private val binding: FragmentForecastBinding by viewBinding()

    @Inject
    lateinit var presenter: ForecastContract.Presenter

    private val forecastAdapter = ForecastRVAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()

        binding.recyclerView.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun updateToolbarTitle(text: String) {
        activity?.let {
            (it as MainActivity).presenter.changeToolbarTitle(text)
        }
    }

    override fun showForecastList(forecastList: List<Any>) {
        forecastAdapter.submitList(forecastList)
    }
}