package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.AreaNameModel

data class AreaNameResponse(
    @SerializedName( "value")
    var value: String? = null
): MapAbleToModel<AreaNameModel>{
    override fun toModel(): AreaNameModel {
        return AreaNameModel(
            value = value
        )
    }

}