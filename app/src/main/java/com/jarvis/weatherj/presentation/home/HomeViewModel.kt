package com.jarvis.weatherj.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherj.common.LOADING
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.domain.model.model.demo.WeatherHourModel
import com.jarvis.weatherj.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.presentation.base.BaseViewModel
import com.jarvis.weatherj.presentation.base.data.StateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var dataWeather = MutableLiveData<DataModel>()

    fun loadDataWeather() {
        mLoading.postValue(LOADING.START)
        viewModelScope.launch {
            val data = weatherUseCase("")
            when (data.status) {
                StateData.DataStatus.SUCCESS -> {
                    dataWeather.postValue(data.data)
                }
                StateData.DataStatus.ERROR -> {
                    mError.postValue(data.error)
                }
                else -> {
                    mError.postValue(data.error)
                }
            }
            mLoading.postValue(LOADING.END)
        }
    }

    fun getListWeatherHour(data: DataModel): MutableList<WeatherHourModel> {
        val listData = mutableListOf<WeatherHourModel>()

        data.weather?.flatMap {
            it.hourly?.map { hourlyModel ->
                listData.add(
                    WeatherHourModel(
                        hourlyModel.time, hourlyModel.tempC, hourlyModel.weatherCode
                    )
                )
            } ?: mutableListOf()
        }

        return listData
    }
}
