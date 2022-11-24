package com.jarvis.weatherj.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.jarvis.weatherj.domain.model.response.CurrentConditionResponse
import com.jarvis.weatherj.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.presentation.base.BaseViewModel
import com.jarvis.weatherj.presentation.base.data.StateData
import com.jarvis.weatherj.presentation.base.data.StateLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var data = MutableLiveData<StateData<List<CurrentConditionResponse>>>()

    fun loadDataWeather() {
        viewModelScope.launch {
            data.value = weatherUseCase.fetchDataWeatherByCity("thai_binh")
        }
    }
}

