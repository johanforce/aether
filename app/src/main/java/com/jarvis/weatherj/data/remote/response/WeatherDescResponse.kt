package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.WeatherDescModel

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