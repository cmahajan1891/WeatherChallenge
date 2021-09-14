package com.example.androidchallenge.network.models

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val current: CurrentWeather,
    val daily: List<DailyWeather>
)

data class CurrentWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    val weather: List<Weather>
)

data class DailyWeather(
    @SerializedName("dt") val dt: Long,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int,
    @SerializedName("moonrise") val moonrise: Int,
    @SerializedName("moonset") val moonset: Int,
    @SerializedName("moon_phase") val moon_phase: Double,
    @SerializedName("temp") val temp: Temp,
    @SerializedName("feels_like") val feels_like: FeelsLike,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dew_point: Double,
    @SerializedName("wind_speed") val wind_speed: Double,
    @SerializedName("wind_deg") val wind_deg: Int,
    @SerializedName("wind_gust") val wind_gust: Double,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val clouds: Int,
    @SerializedName("pop") val pop: Float,
    @SerializedName("uvi") val uvi: Double
)

data class FeelsLike(
    @SerializedName("day") val day: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val eve: Double,
    @SerializedName("morn") val morn: Double
)


data class Temp(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val eve: Double,
    @SerializedName("morn") val morn: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)


/*
{
    "lat": 40.12,
    "lon": -96.66,
    "timezone": "America/Chicago",
    "timezone_offset": -18000,
    "current": {
    "dt": 1595243443,
    "sunrise": 1595243663,
    "sunset": 1595296278,
    "temp": 293.28,
    "feels_like": 293.82,
    "pressure": 1016,
    "humidity": 100,
    "dew_point": 293.28,
    "uvi": 10.64,
    "clouds": 90,
    "visibility": 10000,
    "wind_speed": 4.6,
    "wind_deg": 310,
    "weather": [
    {
        "id": 501,
        "main": "Rain",
        "description": "moderate rain",
        "icon": "10n"
    },
    {
        "id": 201,
        "main": "Thunderstorm",
        "description": "thunderstorm with rain",
        "icon": "11n"
    }
    ],
    "rain": {
        "1h": 2.93
    }
},
    }*/