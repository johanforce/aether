package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.WeatherUrlResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherUrlModel(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: WeatherUrlResponse): WeatherUrlModel {
            val model = WeatherUrlModel()
            model.apply {
                value = entity.value
            }
            return model
        }

        fun convertFromEntity(entity: List<WeatherUrlResponse>): List<WeatherUrlModel> {
            return entity.map {
                val model = WeatherUrlModel()
                model.apply {
                    value = it.value
                }
            }
        }
    }
}