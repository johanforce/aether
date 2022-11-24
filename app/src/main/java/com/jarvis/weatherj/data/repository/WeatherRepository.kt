package com.jarvis.weatherj.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.response.WeatherResponse
import com.jarvis.weatherj.presentation.base.data.ErrorResponse

interface WeatherRepository {
    suspend fun getWeatherById(id: Int): CurrentConditionEntity?
    suspend fun fetchDataWeatherByCity(city: String): NetworkResponse<WeatherResponse, ErrorResponse>
}
