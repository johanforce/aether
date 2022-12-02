package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.response.demo.WeatherIconUrlResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherIconUrlModel(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: WeatherIconUrlResponse): WeatherIconUrlModel {
            val model = WeatherIconUrlModel()
            model.apply {
                value = entity.value
            }
            return model
        }

        fun convertFromEntity(entity: List<WeatherIconUrlResponse>): List<WeatherIconUrlModel> {
            return entity.map {
                val model = WeatherIconUrlModel()
                model.apply {
                    value = it.value
                }
            }
        }
    }
}