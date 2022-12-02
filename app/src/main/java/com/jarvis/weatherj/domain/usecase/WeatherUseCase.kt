package com.jarvis.weatherj.domain.usecase

import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.presentation.base.data.StateData
import javax.inject.Inject

class WeatherUseCase @Inject constructor() {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    suspend operator fun invoke(city: String? = null): StateData<DataModel>{
        return weatherRepository.fetchDataWeatherByCity(city)
    }
}
