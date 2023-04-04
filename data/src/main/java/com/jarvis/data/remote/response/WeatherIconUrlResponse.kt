package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.WeatherIconUrlModel

data class WeatherIconUrlResponse(
    @SerializedName("value")
    val value: String? = null
) : MapAbleToModel<WeatherIconUrlModel> {
    override fun toModel(): WeatherIconUrlModel {
        return WeatherIconUrlModel(
            value = value
        )
    }

}
