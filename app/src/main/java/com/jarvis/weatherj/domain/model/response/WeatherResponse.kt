package com.jarvis.weatherj.domain.model.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName( "current_condition")
    var current_condition: List<CurrentConditionResponse>? = null
)