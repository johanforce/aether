package com.jarvis.weatherj.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.domain.model.model.demo.WeatherHourModel
import com.jarvis.weatherj.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.presentation.base.BaseViewModel
import com.jarvis.weatherj.presentation.pref.AppPrefs
import com.jarvis.weatherj.presentation.pref.SharedPrefsKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var dataWeather = MutableLiveData<DataModel>()

    fun loadDataWeather(city: String) {
        executeTask {
            mLoading.value = true
            val data = withContext(Dispatchers.IO) {
                weatherUseCase(city)
            }
            dataWeather.value = data
            AppPrefs.save(SharedPrefsKey.KEY_PREF_DATA, data)
            AppPrefs.save(SharedPrefsKey.KEY_PREF_DATA_TIME, System.currentTimeMillis())
            mLoading.value = false
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
