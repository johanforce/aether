package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.AreaNameModel

data class AreaNameResponse(
    @SerializedName("value")
    var value: String? = null
) : MapAbleToModel<AreaNameModel> {
    override fun toModel(): AreaNameModel {
        return AreaNameModel(
            value = value
        )
    }

}
