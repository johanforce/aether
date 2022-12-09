package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.response.demo.RequestResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestModel(
    var query: String? = null,
    var type: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: RequestResponse): RequestModel {
            val model = RequestModel()
            model.apply {
                type = entity.type
                query = entity.query
            }
            return model
        }

        fun convertFromEntity(entity: List<RequestResponse>): List<RequestModel> {
            return entity.map {
                val model = RequestModel()
                model.apply {
                    type = it.type
                    query = it.query
                }
            }
        }
    }
}