package com.jarvis.weatherj.presentation.main

import androidx.lifecycle.MutableLiveData
import com.jarvis.weatherj.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    var tempFrag = MutableLiveData<Int>()

    val isMenuExpanded = MutableLiveData(false)
}
