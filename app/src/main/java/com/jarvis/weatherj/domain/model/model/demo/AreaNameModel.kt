package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.AreaNameResponse
import kotlinx.parcelize.Parcelize

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
