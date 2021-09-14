package com.example.androidchallenge.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.R
import com.example.androidchallenge.network.models.DailyWeather
import com.example.androidchallenge.utils.formatTemperature
import com.google.android.material.textview.MaterialTextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherForecastViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.daily_forecast_item, parent, false)
) {

    private var dayOfWeek: MaterialTextView
    private val feelsLikeTemp: MaterialTextView
    private val temp: MaterialTextView

    init {
        dayOfWeek = itemView.findViewById(R.id.day)
        feelsLikeTemp = itemView.findViewById(R.id.feels_like_value)
        temp = itemView.findViewById(R.id.temperature_value)
    }

    fun bind(data: DailyWeather) {
        formatDate(data)
        temp.formatTemperature(temp.context.getString(
                R.string.temp_degree,
                data.temp.day.roundToInt()
            ), 0.4f)
        feelsLikeTemp.formatTemperature(temp.context.getString(R.string.temp_degree, data.feels_like.day.roundToInt()), 0.4f)
    }

    private fun formatDate(data: DailyWeather) {
        val simpleDateFormatter = SimpleDateFormat("EEEE", ConfigurationCompat.getLocales(dayOfWeek.resources.configuration)[0])
        val date = Date(data.dt * 1000L)
        val calendar = Calendar.getInstance()
        calendar.time = date
        dayOfWeek.text = simpleDateFormatter.format(date)
    }

}
