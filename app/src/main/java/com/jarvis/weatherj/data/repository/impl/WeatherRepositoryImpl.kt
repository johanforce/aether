package com.jarvis.weatherj.data.repository.impl

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.data.datasource.AppDatabase
import com.jarvis.weatherj.data.remote.WeatherApi
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.domain.model.response.demo.DataResponse
import com.jarvis.weatherj.presentation.base.data.StateData
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

    override suspend fun fetchDataWeatherByCity(city: String?): StateData<DataModel> {
        val dataWeather: StateData<DataModel> = StateData()
        when (val response = weatherApi.fetchDataWeatherByCity(city)) {
            is NetworkResponse.Success -> {
                response.body.let {
                    dataWeather.data = DataModel.convertFromEntity(it)
                    dataWeather.status = StateData.DataStatus.SUCCESS
                }
            }
            is NetworkResponse.Error -> {
                dataWeather.error = response.error
                dataWeather.status = StateData.DataStatus.ERROR
            }
            else -> {}
        }
        return dataWeather
    }
}
