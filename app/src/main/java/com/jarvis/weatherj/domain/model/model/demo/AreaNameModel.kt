package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.response.demo.AreaNameResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AreaNameModel(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: List<AreaNameResponse>): List<AreaNameModel> {
            return entity.map {
                val model = AreaNameModel()
                model.apply {
                    value = it.value
                }
            }
        }
    }
}