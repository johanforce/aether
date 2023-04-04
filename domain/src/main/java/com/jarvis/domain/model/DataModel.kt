package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    var currentCondition: List<CurrentConditionModel>? = null,
    var nearestArea: List<NearestAreaModel>? = null,
    var request: List<RequestModel>? = null,
    var weather: List<WeatherModel>? = null
) : Parcelable
