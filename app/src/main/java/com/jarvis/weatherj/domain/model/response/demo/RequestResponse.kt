package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class RequestResponse(
    @SerializedName("query")
    var query: String? = null,
    @SerializedName("type")
    var type: String? = null
)