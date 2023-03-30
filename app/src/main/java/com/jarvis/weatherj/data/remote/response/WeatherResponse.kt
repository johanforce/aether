package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.WeatherModel

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
) : MapAbleToModel<WeatherModel> {
    override fun toModel(): WeatherModel {
        return WeatherModel(
            astronomy = astronomy?.map { it.toModel() },
            avgtempC = avgtempC,
            avgtempF = avgtempF,
            date = date,
            hourly = hourly?.map { it.toModel() },
            maxtempC = maxtempC,
            maxtempF = maxtempF,
            mintempC = mintempC,
            mintempF = mintempF,
            sunHour = sunHour,
            totalSnow_cm = totalSnow_cm,
            uvIndex = uvIndex
        )
    }

}