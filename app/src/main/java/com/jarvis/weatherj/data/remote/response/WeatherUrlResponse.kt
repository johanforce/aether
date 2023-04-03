package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.WeatherUrlModel

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
