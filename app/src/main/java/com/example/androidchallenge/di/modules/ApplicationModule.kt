package com.example.androidchallenge.di.modules

import com.example.androidchallenge.BuildConfig
import com.example.androidchallenge.WeatherApplication
import com.example.androidchallenge.network.NetworkHelper
import com.example.androidchallenge.network.Networking
import com.example.androidchallenge.network.api.WeatherApi
import com.example.androidchallenge.utils.rx.RxSchedulerProvider
import com.example.androidchallenge.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: WeatherApplication) {

    @Provides
    @Singleton
    fun providesWeatherApi(): WeatherApi =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10485760L // 10 * 1024 * 1024 = 10MB
        )

    @Provides
    @Singleton
    fun provideApplication(): WeatherApplication = application as WeatherApplication

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

}
