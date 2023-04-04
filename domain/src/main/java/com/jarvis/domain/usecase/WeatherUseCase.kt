package com.jarvis.domain.usecase

import com.jarvis.domain.responsitory.WeatherRepository
import com.jarvis.domain.model.DataModel
import javax.inject.Inject

class WeatherUseCase @Inject constructor() {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    suspend operator fun invoke(city: String? = null): DataModel {
        return weatherRepository.fetchDataWeatherByCity(city)
    }
}
