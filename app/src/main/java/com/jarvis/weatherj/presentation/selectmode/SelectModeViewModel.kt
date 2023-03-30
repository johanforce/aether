package com.jarvis.weatherj.presentation.selectmode

import androidx.lifecycle.MutableLiveData
import com.jarvis.weatherj.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectModeViewModel @Inject constructor() :
    BaseViewModel() {

    val themeMode = MutableLiveData<Int>()

    fun selectMode(mode: Int) {
        if (mode == this.themeMode.value) {
            return
        }

        themeMode.value = mode
    }
}
