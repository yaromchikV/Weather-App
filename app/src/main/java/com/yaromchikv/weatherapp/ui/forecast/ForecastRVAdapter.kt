package com.yaromchikv.weatherapp.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yaromchikv.weatherapp.databinding.ItemDayBinding
import com.yaromchikv.weatherapp.databinding.ItemWeatherBinding
import com.yaromchikv.weatherapp.domain.model.forecast.ForecastData
import javax.inject.Singleton

@Singleton
class ForecastRVAdapter : ListAdapter<Any, ForecastRVAdapter.ItemViewHolder>(DIFF_CALLBACK) {

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(item: Any)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            WEATHER_VIEW_TYPE -> {
                val binding = ItemWeatherBinding.inflate(layoutInflater, parent, false)
                WeatherViewHolder(binding)
            }
            else -> {
                val binding = ItemDayBinding.inflate(layoutInflater, parent, false)
                DayViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        when (holder.itemViewType) {
            WEATHER_VIEW_TYPE -> {
                val viewHolder = holder as WeatherViewHolder
                viewHolder.bind(getItem(position))
            }
            DAY_VIEW_TYPE -> {
                val viewHolder = holder as DayViewHolder
                viewHolder.bind(getItem(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is ForecastData) WEATHER_VIEW_TYPE else DAY_VIEW_TYPE
    }

    inner class WeatherViewHolder(
        private val binding: ItemWeatherBinding
    ) : ItemViewHolder(binding.root) {

        override fun bind(item: Any) {
            val forecast = item as ForecastData
            with(binding) {
                //
            }
        }
    }

    inner class DayViewHolder(
        private val binding: ItemDayBinding
    ) : ItemViewHolder(binding.root) {

        override fun bind(item: Any) {
            val day = item as String
            binding.day.text = day
        }
    }

    companion object {
        private const val WEATHER_VIEW_TYPE = 1
        private const val DAY_VIEW_TYPE = 0

        object DIFF_CALLBACK : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is String && newItem is String)
                    oldItem == newItem
                else if (oldItem is ForecastData && newItem is ForecastData)
                    oldItem.datetime == newItem.datetime
                else false
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return if (oldItem is String && newItem is String)
                    oldItem.hashCode() == newItem.hashCode()
                else if (oldItem is ForecastData && newItem is ForecastData)
                    oldItem.hashCode() == newItem.hashCode()
                else false
            }
        }
    }
}