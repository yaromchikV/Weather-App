package com.yaromchikv.weatherapp.ui.weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.yaromchikv.weatherapp.R
import com.yaromchikv.weatherapp.databinding.FragmentWeatherBinding
import com.yaromchikv.weatherapp.domain.model.Weather
import com.yaromchikv.weatherapp.ui.common.Utils.getDirection
import com.yaromchikv.weatherapp.ui.common.Utils.getIcon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather), WeatherContract.View {

    private val binding: FragmentWeatherBinding by viewBinding()

    @Inject
    lateinit var presenter: WeatherContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()

        binding.shareButton.setOnClickListener {
            presenter.onShareButtonClicked()
        }
    }

    override fun showWeather(weather: Weather) {
        with(binding) {
            val weatherImage = getIcon(weather.weatherData[0].icon)
            val city = getString(R.string.city, weather.city, weather.location.country)
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

            this.weatherImage.setImageResource(weatherImage)
            this.city.text = city
            this.weather.text = weatherText
            this.humidityText.text = humidity
            this.volumeIcon.setImageResource(volumeImage)
            this.volumeText.text = volumeText
            this.pressureText.text = pressure
            this.windSpeedText.text = windSpeed
            this.windDirectionText.text = windDirection
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}