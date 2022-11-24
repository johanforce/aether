package com.jarvis.weatherj.domain.model.model

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.response.WeatherResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    val current_condition: List<CurrentConditionModel>,
): Parcelable {

}