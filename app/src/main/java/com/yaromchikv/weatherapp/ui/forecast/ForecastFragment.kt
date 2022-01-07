package com.yaromchikv.weatherapp.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentForecastBinding
import com.yaromchikv.weatherapp.ui.MainActivity
import com.yaromchikv.weatherapp.ui.common.LocationState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment : Fragment(), ForecastContract.View {

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: ForecastContract.Presenter

    @Inject
    lateinit var forecastAdapter: ForecastRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()

        binding.retryButton.setOnClickListener {
            presenter.onRetryButtonClicked()
        }
    }

    override fun setupRVAdapter() {
        binding.recyclerView.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }
    }

    override fun updateToolbarTitle(text: String) {
        activity?.let { (it as MainActivity).presenter.changeToolbarTitle(text) }
    }

    override fun showForecast(forecastList: List<Any>) {
        forecastAdapter.submitList(forecastList)
    }

    override fun showError(message: String?) {
        with(binding) {
            progressBar.isVisible = false
            recyclerView.isVisible = false

            retryButton.isVisible = true
            error.isVisible = true
            error.text = message ?: getString(R.string.connection_error)
        }
    }

    override fun hideError() {
        with(binding) {
            recyclerView.isVisible = true
            error.isVisible = false
            retryButton.isVisible = false
        }
    }

    override fun showProgressBar() {
        binding.progressBar.isVisible = true
    }

    override fun hideProgressBar() {
        binding.progressBar.isVisible = false
    }

    override fun reloadData() {
        activity?.let { (it as MainActivity).presenter.readyToLoad() }
    }

    override fun updatePosition() {
        presenter.fetchForecast()
    }

    override fun getPosition(): LocationState? {
        return activity?.let { (it as MainActivity).presenter.getLocation() }
    }
}
