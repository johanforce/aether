package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.RegionModel

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
