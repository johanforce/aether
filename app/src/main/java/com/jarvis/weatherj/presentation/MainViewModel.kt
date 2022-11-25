package com.jarvis.weatherj.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherj.common.LOADING
import com.jarvis.weatherj.domain.model.response.CurrentConditionResponse
import com.jarvis.weatherj.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.presentation.base.BaseViewModel
import com.jarvis.weatherj.presentation.base.data.StateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var data = MutableLiveData<List<CurrentConditionResponse>>()

    fun loadDataWeather() {
        viewModelScope.launch {
            weatherUseCase("thai_binh")
                .onStart {
                    mLoading.postValue(LOADING.START)
                }.catch { exception ->
                    mLoading.postValue(LOADING.END)
                    mError.postValue(exception)
                }.collect { result ->
                    when (result.status) {
                        StateData.DataStatus.SUCCESS -> {
                            data.value = result.data?.current_condition
                        }
                        StateData.DataStatus.ERROR -> {
                            mError.postValue(result.error)
                        }
                        else -> {}
                    }
                    mLoading.postValue(LOADING.END)
                }
        }
    }
}

