package com.jarvis.domain.responsitory

import com.jarvis.domain.model.DataModel

interface WeatherRepository {
    suspend fun fetchDataWeatherByCity(city: String? = null): DataModel
}
