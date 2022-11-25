package com.jarvis.weatherj.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jarvis.weatherj.domain.model.response.CurrentConditionResponse
import com.jarvis.weatherj.domain.usecase.WeatherUseCase
import com.jarvis.weatherj.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    var dataApi1 = MutableLiveData<MutableList<CurrentConditionResponse>>()
    var dataApi2 = MutableLiveData<MutableList<CurrentConditionResponse>>()
    var dataApi3 = MutableLiveData<MutableList<CurrentConditionResponse>>()
    var dataApi4 = MutableLiveData<MutableList<CurrentConditionResponse>>()

    fun loadDataWeather() {
//        viewModelScope.launch {
//            weatherUseCase("thai_binh")
//                .onStart {
//                    mLoading.postValue(LOADING.START)
//                }.catch { exception ->
//                    mLoading.postValue(LOADING.END)
//                    mError.postValue(exception)
//                }.collect { result ->
//                    when (result.status) {
//                        StateData.DataStatus.SUCCESS -> {
//                            data.value = result.data?.current_condition
//                        }
//                        StateData.DataStatus.ERROR -> {
//                            mError.postValue(result.error)
//                        }
//                        else -> {}
//                    }
//                    mLoading.postValue(LOADING.END)
//                }
//        }

        viewModelScope.launch {
            val listName = listOf(
                0 to "thai_binh",
                1 to "hanoi",
                2 to "bangkok",
                3 to "tuyen_quang"
            ).asFlow().flatMapMerge { (index, objectParam) ->
                flow {
                    when (index) {
                        0 -> emit(weatherUseCase(objectParam))
                        1 -> emit(weatherUseCase(objectParam))
                        2 -> emit(weatherUseCase(objectParam))
                        else -> {
                            emit(weatherUseCase(objectParam))
                        }
                    }
                }
            }.toList()


            listName.mapIndexed { index, data ->
                when (index) {
                    0 -> dataApi1.postValue(data.data?.current_condition)
                    1 -> dataApi2.postValue(data.data?.current_condition)
                    2 -> dataApi3.postValue(data.data?.current_condition)
                    3 -> dataApi4.postValue(data.data?.current_condition)
                }
            }
        }
    }
}
