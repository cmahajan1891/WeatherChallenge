package com.example.androidchallenge

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.androidchallenge.di.components.DaggerActivityComponent
import com.example.androidchallenge.di.modules.ActivityModule
import com.example.androidchallenge.ui.currentweather.CurrentWeatherViewModel
import com.example.androidchallenge.utils.GpsUtils
import com.example.androidchallenge.utils.display.Toaster
import javax.inject.Inject

const val LOCATION_REQUEST = 100
const val GPS_REQUEST = 101

class MainActivity : AppCompatActivity() {

    // Shared view model
    @Inject
    lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    private var isGPSEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupObservers()
        GpsUtils(this).turnGPSOn(object : GpsUtils.OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                this@MainActivity.isGPSEnabled = isGPSEnable
            }
        })
    }

    private fun setupObservers() {
        currentWeatherViewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        currentWeatherViewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPSEnabled = true
                invokeLocationAction()
            }
        }
    }

    private fun startLocationUpdate() {
        currentWeatherViewModel.getLocationData().observe(this, Observer {
            it.data?.run { currentWeatherViewModel.requestWeatherInfo(it.data.longitude, it.data.latitude) }
        })
    }

    private fun invokeLocationAction() {
        when {
            !isGPSEnabled -> currentWeatherViewModel.updateErrorMessage(getString(R.string.enable_gps))
            isPermissionsGranted() -> startLocationUpdate()
            shouldShowRequestPermissionRationale() -> currentWeatherViewModel.updateErrorMessage(getString(R.string.permission_request))
            else -> ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_REQUEST)
        }
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as WeatherApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this@MainActivity)
    }

    private fun isPermissionsGranted() = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun shouldShowRequestPermissionRationale() = ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) &&
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }

    private fun showMessage(message: String) = applicationContext?.let { Toaster.show(it, message) }

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

}
