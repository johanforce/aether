package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstronomyModel(
    var moonIllumination: String? = null,
    var moonPhase: String? = null,
    var moonrise: String? = null,
    var moonset: String? = null,
    var sunrise: String? = null,
    var sunset: String? = null
) : Parcelable
