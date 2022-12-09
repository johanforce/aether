package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class WeatherDescResponse(
    @SerializedName("value")
    var value: String? = null
)