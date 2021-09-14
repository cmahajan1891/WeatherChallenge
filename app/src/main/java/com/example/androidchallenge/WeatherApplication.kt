package com.example.androidchallenge

import android.app.Application
import com.example.androidchallenge.di.components.ApplicationComponent
import com.example.androidchallenge.di.components.DaggerApplicationComponent
import com.example.androidchallenge.di.modules.ApplicationModule

class WeatherApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        injectDependencies()
        super.onCreate()
    }

    private fun injectDependencies() {
        applicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this@WeatherApplication))
                .build()
        applicationComponent.inject(this@WeatherApplication)
    }
}
