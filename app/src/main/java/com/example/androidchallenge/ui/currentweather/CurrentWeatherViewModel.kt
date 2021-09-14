package com.example.androidchallenge.ui.currentweather

import android.location.Geocoder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.androidchallenge.R
import com.example.androidchallenge.WeatherApplication
import com.example.androidchallenge.data.repository.WeatherRepository
import com.example.androidchallenge.network.NetworkHelper
import com.example.androidchallenge.network.livedata.LocationLiveData
import com.example.androidchallenge.network.models.CurrentWeather
import com.example.androidchallenge.network.models.WeatherData
import com.example.androidchallenge.utils.common.Resource
import com.example.androidchallenge.utils.common.Status
import com.example.androidchallenge.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

class CurrentWeatherViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val networkHelper: NetworkHelper,
    private val weatherRepository: WeatherRepository,
    private val application: WeatherApplication
) : ViewModel() {

    private val currentWeatherLiveData: MutableLiveData<Resource<CurrentWeather>> = MutableLiveData()
    private val weatherLiveData: MutableLiveData<Resource<WeatherData>> = MutableLiveData()
    private val locationData = LocationLiveData(application)
    private val cityName: MutableLiveData<String> = MutableLiveData()
    private val geocoder = Geocoder(application)

    fun getCityName() = cityName

    fun getLocationData() = locationData

    fun getCurrentWeatherData(): LiveData<CurrentWeather> = Transformations.map(currentWeatherLiveData) { it.data }

    fun getWeatherData(): LiveData<WeatherData> = Transformations.map(weatherLiveData) { it.data }

    fun requestWeatherInfo(long: Double, lat: Double) {
        if (currentWeatherLiveData.value == null && checkInternetConnectionWithMessage() && locationData.value?.data != null) {
            currentWeatherLiveData.postValue(Resource.loading())
            geocoder.getFromLocation(lat, long, 1).firstOrNull()?.let {
                cityName.postValue(it.locality)
            }
            compositeDisposable.add(
                weatherRepository.fetchWeatherForecast(long, lat)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            currentWeatherLiveData.postValue(Resource.success(it.current))
                            weatherLiveData.postValue(Resource.success(it))
                        },
                        {
                            handleNetworkError(it)
                            currentWeatherLiveData.postValue(Resource.error())
                            weatherLiveData.postValue(Resource.error())
                        })
            )
        }
    }


    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    private fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    private fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status) {
                    -1 -> messageStringId.postValue(Resource.error(R.string.network_default_error))
                    0 -> messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    HttpsURLConnection.HTTP_INTERNAL_ERROR ->
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    HttpsURLConnection.HTTP_UNAVAILABLE ->
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    else -> messageString.postValue(Resource.error(message))
                }
            }
        }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun updateErrorMessage(message: String) {
        messageString.postValue(Resource.error(message))
    }

}
