package com.jarvis.weatherj.data.repository.impl

import com.jarvis.weatherj.data.remote.WeatherApi
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.model.demo.DataModel
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
