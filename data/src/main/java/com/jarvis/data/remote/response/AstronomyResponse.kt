package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.AstronomyModel

data class AstronomyResponse(
    @SerializedName("moon_illumination")
    var moonIllumination: String? = null,
    @SerializedName("moon_phase")
    var moonPhase: String? = null,
    @SerializedName("moonrise")
    var moonrise: String? = null,
    @SerializedName("moonset")
    var moonset: String? = null,
    @SerializedName("sunrise")
    var sunrise: String? = null,
    @SerializedName("sunset")
    var sunset: String? = null
) : MapAbleToModel<AstronomyModel> {
    override fun toModel(): AstronomyModel {
        return AstronomyModel(
            moonIllumination = moonIllumination,
            moonPhase = moonPhase,
            moonrise = moonrise,
            moonset = moonset,
            sunrise = sunrise,
            sunset = sunset,
        )
    }

}
