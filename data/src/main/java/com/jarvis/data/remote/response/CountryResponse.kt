package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.CountryModels

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
