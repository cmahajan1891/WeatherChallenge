package com.example.androidchallenge.di.modules

import androidx.lifecycle.ViewModelStoreOwner
import com.example.androidchallenge.WeatherApplication
import com.example.androidchallenge.data.repository.WeatherRepository
import com.example.androidchallenge.di.scopes.ActivityScope
import com.example.androidchallenge.network.NetworkHelper
import com.example.androidchallenge.ui.currentweather.CurrentWeatherViewModel
import com.example.androidchallenge.utils.getViewModel
import com.example.androidchallenge.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(
    private val viewModelStoreOwner: ViewModelStoreOwner
) {

    @ActivityScope
    @Provides
    fun providesViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        weatherRepository: WeatherRepository,
        application: WeatherApplication
    ) = getViewModel(
        viewModelStoreOwner,
        CurrentWeatherViewModel::class
    ) {
        CurrentWeatherViewModel(
            schedulerProvider,
            compositeDisposable,
            networkHelper,
            weatherRepository,
            application
        )
    }
}
