package com.jarvis.weatherj.data.repository

import com.jarvis.weatherj.domain.model.model.demo.DataModel

interface WeatherRepository {
    suspend fun fetchDataWeatherByCity(city: String? = null): DataModel
}
