package com.example.androidchallenge.data.repository

import com.example.androidchallenge.network.api.WeatherApi
import com.example.androidchallenge.network.models.WeatherData
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {
    fun fetchWeatherForecast(lon: Double, lat: Double): Single<WeatherData> = weatherApi.getWeatherForecast(lon = lon.toString(), lat = lat.toString())
}
