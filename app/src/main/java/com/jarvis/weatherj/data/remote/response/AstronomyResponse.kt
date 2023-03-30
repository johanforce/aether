package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.AstronomyModel

data class AstronomyResponse(
    @SerializedName("moon_illumination")
    var moon_illumination: String? = null,
    @SerializedName("moon_phase")
    var moon_phase: String? = null,
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
            moonIllumination = moon_illumination,
            moonPhase = moon_phase,
            moonrise = moonrise,
            moonset = moonset,
            sunrise = sunrise,
            sunset = sunset,
        )
    }

}