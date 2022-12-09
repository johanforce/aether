package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class RegionResponse(
    @SerializedName("value")
    var value: String? = null
)