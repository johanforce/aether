package com.jarvis.weatherj.data.repository.impl

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.data.datasource.AppDatabase
import com.jarvis.weatherj.data.remote.WeatherApi
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.response.WeatherResponse
import com.jarvis.weatherj.presentation.base.data.StateData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    override suspend fun fetchDataWeatherByCity(city: String): Flow<StateData<WeatherResponse>> {
        return flow {
            val dataWeather: StateData<WeatherResponse> = StateData()
            when (val response = weatherApi.fetchDataWeatherByCity(city)) {
                is NetworkResponse.Success -> {
                    emit(dataWeather.success(response.body))
                }
                is NetworkResponse.Error -> {
                    emit(dataWeather.error(response.error))
                }
                else -> {}
            }
        }
    }
}
