package com.jarvis.weatherj.di

import android.app.Application
import androidx.room.Room
import com.jarvis.weatherj.data.datasource.AppDatabase
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.data.repository.impl.WeatherRepositoryImpl
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
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository{
        return weatherRepositoryImpl
    }
}
