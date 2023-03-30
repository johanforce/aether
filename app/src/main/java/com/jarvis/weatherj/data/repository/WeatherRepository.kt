package com.jarvis.weatherj.data.repository

import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.model.demo.DataModel

interface WeatherRepository {
    suspend fun getWeatherById(id: Int): CurrentConditionEntity?
    suspend fun fetchDataWeatherByCity(city: String? = null): DataModel
}
