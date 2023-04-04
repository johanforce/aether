package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.RegionModel

data class RegionResponse(
    @SerializedName("value")
    var value: String? = null
) : MapAbleToModel<RegionModel> {
    override fun toModel(): RegionModel {
        return RegionModel(
            value = value
        )
    }

}
