package com.app.trendinggithubrepositoriesapp

import android.app.Application
import com.app.trendinggithubrepositoriesapp.di.ApplicationComponent
import com.app.trendinggithubrepositoriesapp.di.DaggerApplicationComponent

class MyApplication: Application() {
    lateinit var  applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}