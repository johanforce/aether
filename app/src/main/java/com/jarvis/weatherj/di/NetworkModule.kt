package com.jarvis.weatherj.di

import com.google.gson.Gson
import com.jarvis.weatherj.BuildConfig
import com.jarvis.data.remote.ApiService
import com.jarvis.data.remote.WeatherApi
import com.jarvis.weatherj.presentation.base.BaseSourceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
    @BaseSourceApi("logging")
    fun provideLogging(): Interceptor {
        return ApiService(BuildConfig.BASE_URL).createLoggingInterceptor()
    }

    @Provides
    @Singleton
    @BaseSourceApi("header")
    fun provideHeader(): Interceptor {
        return ApiService(BuildConfig.BASE_URL).createHeaderInterceptor()
    }

    @Provides
    @Singleton
    @BaseSourceApi("sample")
    fun provideSampleOkHttpClient(
        @BaseSourceApi("logging") logging: Interceptor,
        @BaseSourceApi("header") header: Interceptor
    ): OkHttpClient {
        return ApiService(BuildConfig.BASE_URL).createOkHttpClient(logging, header)
    }

    @Provides
    @Singleton
    @BaseSourceApi("sample")
    fun provideSampleRetrofit(
        @BaseSourceApi("sample") okHttpClient: OkHttpClient
    ): Retrofit {
        return ApiService(BuildConfig.BASE_URL).createRetrofit(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideApiService(@BaseSourceApi("sample") retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}
