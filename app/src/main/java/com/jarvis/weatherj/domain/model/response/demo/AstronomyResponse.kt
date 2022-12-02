package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class AstronomyResponse(
    @SerializedName( "moon_illumination")
    var moon_illumination: String? = null,
    @SerializedName( "moon_phase")
    var moon_phase: String? = null,
    @SerializedName( "moonrise")
    var moonrise: String? = null,
    @SerializedName( "moonset")
    var moonset: String? = null,
    @SerializedName( "sunrise")
    var sunrise: String? = null,
    @SerializedName( "sunset")
    var sunset: String? = null
)