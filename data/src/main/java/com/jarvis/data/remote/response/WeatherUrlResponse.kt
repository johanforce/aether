package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.WeatherUrlModel

data class WeatherUrlResponse(
    @SerializedName("value")
    var value: String? = null
) : MapAbleToModel<WeatherUrlModel> {
    override fun toModel(): WeatherUrlModel {
        return WeatherUrlModel(
            value = value
        )
    }

}
