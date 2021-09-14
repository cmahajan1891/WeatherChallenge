package com.example.androidchallenge.di.components

import com.example.androidchallenge.MainActivity
import com.example.androidchallenge.di.modules.ActivityModule
import com.example.androidchallenge.di.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}
