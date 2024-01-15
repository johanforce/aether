@file:Suppress("UNUSED_PARAMETER")

package com.jarvis.weatherj.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.jarvis.kmm.viewmodel.WeatherViewModel
import com.jarvis.weatherj.data.local.AppDatabase
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.data.repository.impl.WeatherRepositoryImpl
import com.jarvis.weatherj.presentation.common.NetworkConnectionUtil
import com.jarvis.weatherj.presentation.service.NotifyWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideWeatherViewModel(): WeatherViewModel {
        return com.jarvis.kmm.viewmodel.ViewModelProvider.provideWeatherViewModel()
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionUtil(application: Application): NetworkConnectionUtil {
        return NetworkConnectionUtil(application)
    }

    fun inject(fragment: NotifyWorker) {
        // do nothing
    }
}
