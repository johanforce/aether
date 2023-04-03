package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.LangViModel

data class LangViResponse(
    @SerializedName("value")
    var value: String? = null
) : MapAbleToModel<LangViModel> {
    override fun toModel(): LangViModel {
        return LangViModel(
            value = value
        )
    }

}
