package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.CurrentConditionModel

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
) : MapAbleToModel<CurrentConditionModel> {
    override fun toModel(): CurrentConditionModel {
        return CurrentConditionModel(
            FeelsLikeC = FeelsLikeC,
            FeelsLikeF = FeelsLikeF,
            cloudcover = cloudcover,
            humidity = humidity,
            lang_vi = lang_vi?.map { it.toModel() },
            localObsDateTime = localObsDateTime,
            observation_time = observation_time,
            precipInches = precipInches,
            precipMM = precipMM,
            pressure = pressure,
            pressureInches = pressureInches,
            temp_C = temp_C,
            temp_F = temp_F,
            uvIndex = uvIndex,
            visibility = visibility,
            visibilityMiles = visibilityMiles,
            weatherCode = weatherCode,
            weatherDesc = weatherDesc?.map { it.toModel() },
            weatherIconUrl = weatherIconUrl?.map { it.toModel() },
            winddir16Point = winddir16Point,
            winddirDegree = winddirDegree,
            windspeedKmph = windspeedKmph,
            windspeedMiles = windspeedMiles,
        )
    }

}