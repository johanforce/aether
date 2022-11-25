package com.jarvis.weatherj.domain.usecase

import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.response.WeatherResponse
import com.jarvis.weatherj.presentation.base.data.StateData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor() {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    suspend operator fun invoke(city: String): Flow<StateData<WeatherResponse>> {
        return weatherRepository.fetchDataWeatherByCity(city)
    }
}
