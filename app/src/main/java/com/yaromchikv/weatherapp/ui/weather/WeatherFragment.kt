package com.yaromchikv.weatherapp.ui.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.common.Utils.getDirection
import com.yaromchikv.weatherapp.databinding.FragmentWeatherBinding
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.ui.ActivityListener
import com.yaromchikv.weatherapp.ui.common.LocationState
import com.yaromchikv.weatherapp.ui.common.Utils.getIcon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class WeatherFragment : Fragment(), WeatherContract.View {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: WeatherContract.Presenter

    private lateinit var activityListener: ActivityListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()

        with(binding) {
            shareButton.setOnClickListener {
                presenter.onShareButtonClicked()
            }
            retryButton.setOnClickListener {
                presenter.onRetryButtonClicked()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityListener = activity as ActivityListener
    }

    override fun showWeather(weather: Weather, locality: String, country: String) {
        val weatherImage = getIcon(weather.weatherData[0].icon)
        val location = getString(R.string.city, locality, country)
        val weatherText = getString(
            R.string.weather,
            weather.conditions.temperature.roundToInt(),
            weather.weatherData[0].main
        )
        val humidity = getString(R.string.humidity, weather.conditions.humidity)

        val volume = when {
            weather.rain != null -> weather.rain.volume
            weather.snow != null -> weather.snow.volume
            else -> 0.0
        }
        val volumeImage =
            if (volume != 0.0) R.drawable.icon_volume else R.drawable.icon_cloudiness
        val volumeText = if (volume != 0.0) getString(R.string.volume, volume)
        else getString(R.string.cloudiness, weather.clouds.cloudiness)

        val pressure = getString(R.string.pressure, weather.conditions.pressure)
        val windSpeed = getString(R.string.wind_speed, weather.wind.speed)
        val windDirection = getDirection(weather.wind.degrees)

        with(binding) {
            views.isVisible = true

            this.weatherImage.setImageResource(weatherImage)
            this.location.text = location
            this.weather.text = weatherText
            this.humidityText.text = humidity
            this.volumeIcon.setImageResource(volumeImage)
            this.volumeText.text = volumeText
            this.pressureText.text = pressure
            this.windSpeedText.text = windSpeed
            this.windDirectionText.text = windDirection
        }
    }

    override fun showError(message: String?) {
        with(binding) {
            views.isVisible = false

            retryButton.isVisible = true
            error.isVisible = true
            error.text = message ?: getString(R.string.connection_error)
        }
    }

    override fun hideError() {
        with(binding) {
            error.isVisible = false
            retryButton.isVisible = false
        }
    }

    override fun showProgressBar() {
        with(binding) {
            views.isVisible = false
            progressBar.isVisible = true
        }
    }

    override fun hideProgressBar() {
        binding.progressBar.isVisible = false
    }

    override fun openShareActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun updateLocation() {
        presenter.fetchWeather()
    }

    override fun updateToolbarTitle() {
        activityListener.updateToolbarTitle(getString(R.string.today))
    }

    override fun reloadData() {
        activityListener.reloadData()
    }

    override fun getLocation(): LocationState {
        return activityListener.getLocation()
    }
}