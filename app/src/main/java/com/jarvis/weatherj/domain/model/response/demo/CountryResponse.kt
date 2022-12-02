package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName( "value")
    var value: String? = null
)