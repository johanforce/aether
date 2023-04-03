package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.WeatherDescResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDescModel(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: WeatherDescResponse): WeatherDescModel {
            val model = WeatherDescModel()
            model.apply {
                value = entity.value
            }
            return model
        }

        fun convertFromEntity(entity: List<WeatherDescResponse>): List<WeatherDescModel> {
            return entity.map {
                val model = WeatherDescModel()
                model.apply {
                    value = it.value
                }
            }
        }
    }
}
