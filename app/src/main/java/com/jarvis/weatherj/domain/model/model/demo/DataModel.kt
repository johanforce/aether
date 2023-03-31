package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.DataResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    var current_condition: List<CurrentConditionModel>? = null,
    var nearest_area: List<NearestAreaModel>? = null,
    var request: List<RequestModel>? = null,
    var weather: List<WeatherModel>? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: DataResponse): DataModel {
            val model = DataModel()
            model.apply {
                current_condition =
                    CurrentConditionModel.convertFromEntity(entity.current_condition ?: emptyList())
                nearest_area =
                    NearestAreaModel.convertFromEntity(entity.nearest_area ?: emptyList())
                weather = WeatherModel.convertFromEntity(entity.weather ?: emptyList())
                request = RequestModel.convertFromEntity(entity.request ?: emptyList())
            }
            return model
        }
    }
}