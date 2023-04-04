package com.jarvis.data.repository

import com.jarvis.data.remote.WeatherApi
import com.jarvis.domain.responsitory.WeatherRepository
import com.jarvis.domain.model.DataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {
    override suspend fun fetchDataWeatherByCity(city: String?): DataModel {
        return weatherApi.fetchDataWeatherByCity(city).toModel()
    }
}
