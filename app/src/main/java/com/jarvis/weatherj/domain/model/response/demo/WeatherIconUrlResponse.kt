package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class WeatherIconUrlResponse(
    @SerializedName("value")
    val value: String? = null
)