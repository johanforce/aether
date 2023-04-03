package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.LangViResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class LangViModel(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: LangViResponse): LangViModel {
            val model = LangViModel()
            model.apply {
                value = entity.value
            }
            return model
        }

        fun convertFromEntity(entity: List<LangViResponse>): List<LangViModel> {
            return entity.map {
                val model = LangViModel()
                model.apply {
                    value = it.value
                }
            }
        }
    }
}
