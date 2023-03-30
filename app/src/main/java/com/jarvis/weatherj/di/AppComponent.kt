package com.jarvis.weatherj.di

import android.app.Application
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.data.repository.impl.WeatherRepositoryImpl
import com.jarvis.weatherj.presentation.common.NetworkConnectionUtil
import com.jarvis.weatherj.presentation.service.NotifyWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionUtil(application: Application): NetworkConnectionUtil {
        return NetworkConnectionUtil(application)
    }

    fun inject(fragment: NotifyWorker) {
    }
}
