package com.jarvis.weatherj.domain.usecase

import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.data.repository.WeatherRepository
import com.jarvis.weatherj.domain.model.response.CurrentConditionResponse
import com.jarvis.weatherj.domain.model.response.WeatherResponse
import com.jarvis.weatherj.presentation.base.data.ErrorResponse
import com.jarvis.weatherj.presentation.base.data.StateData
import com.jarvis.weatherj.presentation.base.data.StateLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherUseCase @Inject constructor() {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    suspend operator fun invoke(city: String): StateData<List<CurrentConditionResponse>> {
        val dataWeather: StateData<List<CurrentConditionResponse>> = StateData()
        when (val response = weatherRepository.fetchDataWeatherByCity(city)) {
            is NetworkResponse.Success -> {
                response.body.current_condition?.let {
                    dataWeather.data = it
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
