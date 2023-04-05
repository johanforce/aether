package com.jarvis.weatherj.di

import com.google.gson.Gson
import com.jarvis.data.remote.ServiceGenerator
import com.jarvis.data.remote.WeatherApi
import com.jarvis.weatherj.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Declare network component
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideApiService(): WeatherApi {
        return ServiceGenerator.buildRestApi(
            baseUrl = BuildConfig.BASE_URL,
            restApi = WeatherApi::class.java,
            gson = Gson(),
            interceptors = listOf()
        )
    }
}
