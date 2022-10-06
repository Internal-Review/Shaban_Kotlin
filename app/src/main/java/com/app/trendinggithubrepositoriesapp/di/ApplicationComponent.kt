package com.app.trendinggithubrepositoriesapp.di

import android.content.Context
import com.app.trendinggithubrepositoriesapp.ui.DetailsActivity
import com.app.trendinggithubrepositoriesapp.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailsActivity: DetailsActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

}