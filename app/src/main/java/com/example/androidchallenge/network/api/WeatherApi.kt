package com.example.androidchallenge.network.api

import com.example.androidchallenge.network.Endpoints
import com.example.androidchallenge.network.Networking
import com.example.androidchallenge.network.models.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(Endpoints.WEATHER_ONE_CALL_API)
    fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String = Networking.API_KEY
    ): Single<WeatherData>
}
