package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.RegionResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegionModel(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: RegionResponse): RegionModel {
            val model = RegionModel()
            model.apply {
                value = entity.value
            }
            return model
        }

        fun convertFromEntity(entity: List<RegionResponse>): List<RegionModel> {
            return entity.map {
                val model = RegionModel()
                model.apply {
                    value = it.value
                }
            }
        }
    }
}