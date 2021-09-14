package com.example.androidchallenge.di.components

import com.example.androidchallenge.WeatherApplication
import com.example.androidchallenge.di.modules.ApplicationModule
import com.example.androidchallenge.network.NetworkHelper
import com.example.androidchallenge.network.api.WeatherApi
import com.example.androidchallenge.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: WeatherApplication)
    fun getApplication(): WeatherApplication
    fun getNetworkHelper(): NetworkHelper
    fun getWeatherApi(): WeatherApi
    fun getSchedulerProvider(): SchedulerProvider
    fun getCompositeDisposable(): CompositeDisposable
}
