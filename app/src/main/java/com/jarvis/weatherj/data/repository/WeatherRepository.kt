package com.jarvis.weatherj.data.repository

import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.domain.model.response.demo.DataResponse
import com.jarvis.weatherj.presentation.base.data.StateData

interface WeatherRepository {
    suspend fun getWeatherById(id: Int): CurrentConditionEntity?
    suspend fun fetchDataWeatherByCity(city: String? = null): StateData<DataModel>
}
