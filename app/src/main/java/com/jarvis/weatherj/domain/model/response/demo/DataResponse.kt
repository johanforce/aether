package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("current_condition")
    var current_condition: List<CurrentConditionResponse>? = null,
    @SerializedName("nearest_area")
    var nearest_area: List<NearestAreaResponse>? = null,
    @SerializedName("request")
    var request: List<RequestResponse>? = null,
    @SerializedName("weather")
    var weather: List<WeatherResponse>? = null
)