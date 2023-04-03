package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.CurrentConditionModel

data class CurrentConditionResponse(
    @SerializedName("FeelsLikeC")
    var feelsLikeC: String? = null,
    @SerializedName("FeelsLikeF")
    var feelsLikeF: String? = null,
    @SerializedName("cloudcover")
    var cloudcover: String? = null,
    @SerializedName("humidity")
    var humidity: String? = null,
    @SerializedName("lang_vi")
    var langVi: List<LangViResponse>? = null,
    @SerializedName("localObsDateTime")
    var localObsDateTime: String? = null,
    @SerializedName("observation_time")
    var observationTime: String? = null,
    @SerializedName("precipInches")
    var precipInches: String? = null,
    @SerializedName("precipMM")
    var precipMM: String? = null,
    @SerializedName("pressure")
    var pressure: String? = null,
    @SerializedName("pressureInches")
    var pressureInches: String? = null,
    @SerializedName("temp_C")
    var tempC: String? = null,
    @SerializedName("temp_F")
    var tempF: String? = null,
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
            feelsLikeC = feelsLikeC,
            feelsLikeF = feelsLikeF,
            cloudcover = cloudcover,
            humidity = humidity,
            langVi = langVi?.map { it.toModel() },
            localObsDateTime = localObsDateTime,
            observationTime = observationTime,
            precipInches = precipInches,
            precipMM = precipMM,
            pressure = pressure,
            pressureInches = pressureInches,
            tempC = tempC,
            tempF = tempF,
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
