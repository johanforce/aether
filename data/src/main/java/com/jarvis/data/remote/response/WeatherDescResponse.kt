package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.WeatherDescModel

data class WeatherDescResponse(
    @SerializedName("value")
    var value: String? = null
) : MapAbleToModel<WeatherDescModel> {
    override fun toModel(): WeatherDescModel {
        return WeatherDescModel(
            value = value
        )
    }

}
