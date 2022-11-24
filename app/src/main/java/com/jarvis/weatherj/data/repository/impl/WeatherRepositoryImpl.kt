package com.jarvis.weatherj.data.repository.impl

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.data.datasource.AppDatabase
import com.jarvis.weatherj.data.datasource.dao.WeatherDao
import com.jarvis.weatherj.data.remote.WeatherApi
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.response.WeatherResponse
import com.jarvis.weatherj.presentation.base.data.ErrorResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val appDatabase: AppDatabase
) : WeatherRepository {

    override suspend fun getWeatherById(id: Int): CurrentConditionEntity? {
        return appDatabase.weatherDao().getWeatherById(id)
    }

    override suspend fun fetchDataWeatherByCity(city: String): NetworkResponse<WeatherResponse, ErrorResponse> {
        return weatherApi.fetchDataWeatherByCity(city)
    }
}
