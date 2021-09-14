package com.example.androidchallenge.ui.currentweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.androidchallenge.MainActivity
import com.example.androidchallenge.R
import com.example.androidchallenge.utils.display.Toaster
import com.example.androidchallenge.utils.formatTemperature
import com.google.android.material.textview.MaterialTextView
import kotlin.math.roundToInt

class CurrentWeatherFragment : Fragment() {

    private lateinit var viewForecast: Button
    private lateinit var cityName: MaterialTextView
    private lateinit var weatherText: MaterialTextView
    private lateinit var temperature: MaterialTextView
    private lateinit var feelsLikeText: MaterialTextView
    private lateinit var humidityText: MaterialTextView
    private lateinit var pressureText: MaterialTextView
    private lateinit var windText: MaterialTextView
    private lateinit var group: Group

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_current_weather, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupObservers()
    }

    private fun setupView(view: View) {
        viewForecast = view.findViewById(R.id.viewForecast)
        cityName = view.findViewById(R.id.city_name)
        weatherText = view.findViewById(R.id.weather)
        temperature = view.findViewById(R.id.temperature)
        feelsLikeText = view.findViewById(R.id.feels_like_value)
        humidityText = view.findViewById(R.id.humidity_value)
        pressureText = view.findViewById(R.id.atmospheric_pressure_value)
        windText = view.findViewById(R.id.wind_value)
        group = view.findViewById(R.id.group)
        viewForecast.setOnClickListener {
            findNavController().navigate(CurrentWeatherFragmentDirections.actionMainContentFragmentToWeatherForecastFragment())
        }
    }

    private fun setupObservers() {
        val mainActivity = (activity as MainActivity)
        mainActivity.currentWeatherViewModel.messageString.observe(viewLifecycleOwner, Observer {
            it.data?.run { showMessage(this) }
        })

        mainActivity.currentWeatherViewModel.messageStringId.observe(viewLifecycleOwner, Observer {
            it.data?.run { showMessage(this) }
        })

        mainActivity.currentWeatherViewModel.getCityName().observe(viewLifecycleOwner, Observer {
            cityName.text = it
        })

        mainActivity.currentWeatherViewModel.getCurrentWeatherData()
            .observe(viewLifecycleOwner, Observer {
                it?.run {
                    group.visibility = View.VISIBLE
                    weatherText.text = it.weather.first().main
                    temperature.formatTemperature(temperature.context.getString(R.string.temp_degree, it.temp.roundToInt()), 0.4f)
                    feelsLikeText.formatTemperature(feelsLikeText.context.getString(
                            R.string.temp_degree,
                            it.feelsLike.roundToInt()
                        ), 0.4f)
                    humidityText.text = humidityText.context.getString(R.string.humidity_percent, it.humidity)
                    pressureText.text = pressureText.context.getString(R.string.pressure_hPa, it.pressure)
                    windText.text = windText.context.getString(R.string.wind_speed_mps, it.windSpeed.toString())
                }
            })
    }

    private fun showMessage(message: String) = context?.let { Toaster.show(it, message) }

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

}
