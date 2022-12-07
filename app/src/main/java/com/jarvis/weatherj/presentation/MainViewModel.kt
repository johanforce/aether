package com.jarvis.weatherj.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherj.common.LOADING
import com.jarvis.weatherj.domain.model.model.demo.DataModel
import com.jarvis.weatherj.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.presentation.base.BaseViewModel
import com.jarvis.weatherj.presentation.base.data.StateData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var dataWeather = MutableLiveData<DataModel>()

    var tempFrag = MutableLiveData<Int>()

    val isMenuExpanded = MutableLiveData(false)

    fun onClickFrag(temp: Int) {
        if (temp == 0) {
            tempFrag.value = 0
        } else if (temp == 1) {
            tempFrag.value = 1
        }
    }

    fun loadDataWeather() {
        mLoading.value = LOADING.START
        viewModelScope.launch {
            val data = weatherUseCase("thai_binh")
            when (data.status) {
                StateData.DataStatus.SUCCESS -> {
                    dataWeather.value = data.data
                }
                StateData.DataStatus.ERROR -> {
                    mError.value = data.error
                }
                else -> {
                    mError.value = data.error
                }
            }
            mLoading.value = LOADING.END
        }
    }
}
