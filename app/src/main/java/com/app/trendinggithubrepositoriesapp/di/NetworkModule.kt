package com.app.trendinggithubrepositoriesapp.di

import com.app.trendinggithubrepositoriesapp.retrofit.ApiCall
import com.app.trendinggithubrepositoriesapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

}