package com.example.androidchallenge.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.network.models.DailyWeather
import com.example.androidchallenge.ui.viewholders.WeatherForecastViewHolder

class WeatherForecastAdapter(
    private val forecasts: ArrayList<DailyWeather>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherForecastViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeatherForecastViewHolder).bind(forecasts[position])
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    fun appendData(dailyWeather: List<DailyWeather>) {
        val oldCount = itemCount
        this.forecasts.addAll(dailyWeather)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount in 1 until currentCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

}
