package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.WeatherIconUrlModel

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