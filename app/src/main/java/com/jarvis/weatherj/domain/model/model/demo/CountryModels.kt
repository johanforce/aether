package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.CountryResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModels(
    var value: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: List<CountryResponse>): List<CountryModels> {
            return entity.map {
                val model = CountryModels()
                model.apply {
                    value = it.value
                }
            }
        }

        fun convertFromEntity(entity: CountryResponse): CountryModels {
            val model = CountryModels()
            model.apply {
                value = entity.value
            }
            return model
        }
    }
}
