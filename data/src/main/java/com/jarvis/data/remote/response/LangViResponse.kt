package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.LangViModel

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
