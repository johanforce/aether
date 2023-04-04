package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherModel(
    var astronomy: List<AstronomyModel>? = null,
    var avgtempC: String? = null,
    var avgtempF: String? = null,
    var date: String? = null,
    var hourly: List<HourlyModel>? = null,
    var maxtempC: String? = null,
    var maxtempF: String? = null,
    var mintempC: String? = null,
    var mintempF: String? = null,
    var sunHour: String? = null,
    var totalSnowCm: String? = null,
    var uvIndex: String? = null
) : Parcelable
