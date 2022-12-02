package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("astronomy")
    var astronomy: List<AstronomyResponse>? = null,
    @SerializedName("avgtempC")
    var avgtempC: String? = null,
    @SerializedName("avgtempF")
    var avgtempF: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("hourly")
    var hourly: List<HourlyResponse>? = null,
    @SerializedName("maxtempC")
    var maxtempC: String? = null,
    @SerializedName("maxtempF")
    var maxtempF: String? = null,
    @SerializedName("mintempC")
    var mintempC: String? = null,
    @SerializedName("mintempF")
    var mintempF: String? = null,
    @SerializedName("sunHour")
    var sunHour: String? = null,
    @SerializedName("totalSnow_cm")
    var totalSnow_cm: String? = null,
    @SerializedName("uvIndex")
    var uvIndex: String? = null
)