package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class CurrentConditionResponse(
    @SerializedName("FeelsLikeC")
    var FeelsLikeC: String? = null,
    @SerializedName("FeelsLikeF")
    var FeelsLikeF: String? = null,
    @SerializedName("cloudcover")
    var cloudcover: String? = null,
    @SerializedName("humidity")
    var humidity: String? = null,
    @SerializedName("lang_vi")
    var lang_vi: List<LangViResponse>? = null,
    @SerializedName("localObsDateTime")
    var localObsDateTime: String? = null,
    @SerializedName("observation_time")
    var observation_time: String? = null,
    @SerializedName("precipInches")
    var precipInches: String? = null,
    @SerializedName("precipMM")
    var precipMM: String? = null,
    @SerializedName("pressure")
    var pressure: String? = null,
    @SerializedName("pressureInches")
    var pressureInches: String? = null,
    @SerializedName("temp_C")
    var temp_C: String? = null,
    @SerializedName("temp_F")
    var temp_F: String? = null,
    @SerializedName("uvIndex")
    var uvIndex: String? = null,
    @SerializedName("visibility")
    var visibility: String? = null,
    @SerializedName("visibilityMiles")
    var visibilityMiles: String? = null,
    @SerializedName("weatherCode")
    var weatherCode: String? = null,
    @SerializedName("weatherDesc")
    var weatherDesc: List<WeatherDescResponse>? = null,
    @SerializedName("weatherIconUrl")
    var weatherIconUrl: List<WeatherIconUrlResponse>? = null,
    @SerializedName("winddir16Point")
    var winddir16Point: String? = null,
    @SerializedName("winddirDegree")
    var winddirDegree: String? = null,
    @SerializedName("windspeedKmph")
    var windspeedKmph: String? = null,
    @SerializedName("windspeedMiles")
    var windspeedMiles: String? = null
)