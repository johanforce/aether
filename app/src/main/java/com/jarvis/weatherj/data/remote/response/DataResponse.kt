package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.DataModel

data class DataResponse(
    @SerializedName("current_condition")
    var current_condition: List<CurrentConditionResponse>? = null,
    @SerializedName("nearest_area")
    var nearest_area: List<NearestAreaResponse>? = null,
    @SerializedName("request")
    var request: List<RequestResponse>? = null,
    @SerializedName("weather")
    var weather: List<WeatherResponse>? = null
) : MapAbleToModel<DataModel> {
    override fun toModel(): DataModel {
        return DataModel(
            currentCondition = current_condition?.map { it.toModel() },
            nearestArea = nearest_area?.map { it.toModel() },
            weather = weather?.map { it.toModel() },
            request = request?.map { it.toModel() }
        )
    }

}
