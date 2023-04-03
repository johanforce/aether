package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.CountryModels

data class CountryResponse(
    @SerializedName("value")
    var value: String? = null
) : MapAbleToModel<CountryModels> {
    override fun toModel(): CountryModels {
        return CountryModels(
            value = value
        )
    }

}
