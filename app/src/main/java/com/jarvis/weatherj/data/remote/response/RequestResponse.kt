package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.RequestModel

data class RequestResponse(
    @SerializedName("query")
    var query: String? = null,
    @SerializedName("type")
    var type: String? = null
) : MapAbleToModel<RequestModel> {
    override fun toModel(): RequestModel {
        return RequestModel(
            query = query,
            type = type
        )
    }

}