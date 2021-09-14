package com.example.androidchallenge.ui.currentweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchallenge.MainActivity
import com.example.androidchallenge.R
import com.example.androidchallenge.ui.adapters.WeatherForecastAdapter

class WeatherForecastFragment : Fragment() {

    private lateinit var weatherForecast: RecyclerView
    private lateinit var weatherForecastAdapter: WeatherForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_weather_forecast, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupObservers()
    }

    private fun setupView(view: View) {
        weatherForecast = view.findViewById(R.id.weather_forecast)
        weatherForecastAdapter = WeatherForecastAdapter(ArrayList())
        weatherForecast.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = weatherForecastAdapter
        }
    }

    private fun setupObservers() {
        val mainActivity = (activity as MainActivity)
        mainActivity.currentWeatherViewModel.getWeatherData().observe(viewLifecycleOwner, Observer {
            it?.run {
                weatherForecastAdapter.appendData(it.daily)
            }
        })
    }
}
