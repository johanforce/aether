package com.jarvis.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.domain.mapper.MapAbleToModel
import com.jarvis.domain.model.HourlyModel

data class HourlyResponse(
    @SerializedName("DewPointC")
    var dewPointC: String? = null,
    @SerializedName("DewPointF")
    var dewPointF: String? = null,
    @SerializedName("FeelsLikeC")
    var feelsLikeC: String? = null,
    @SerializedName("FeelsLikeF")
    var feelsLikeF: String? = null,
    @SerializedName("HeatIndexC")
    var heatIndexC: String? = null,
    @SerializedName("HeatIndexF")
    var heatIndexF: String? = null,
    @SerializedName("WindChillC")
    var windChillC: String? = null,
    @SerializedName("WindChillF")
    var windChillF: String? = null,
    @SerializedName("WindGustKmph")
    var windGustKmph: String? = null,
    @SerializedName("WindGustMiles")
    var windGustMiles: String? = null,
    @SerializedName("chanceoffog")
    var chanceoffog: String? = null,
    @SerializedName("chanceoffrost")
    var chanceoffrost: String? = null,
    @SerializedName("chanceofhightemp")
    var chanceofhightemp: String? = null,
    @SerializedName("chanceofovercast")
    var chanceofovercast: String? = null,
    @SerializedName("chanceofrain")
    var chanceofrain: String? = null,
    @SerializedName("chanceofremdry")
    var chanceofremdry: String? = null,
    @SerializedName("chanceofsnow")
    var chanceofsnow: String? = null,
    @SerializedName("chanceofsunshine")
    var chanceofsunshine: String? = null,
    @SerializedName("chanceofthunder")
    var chanceofthunder: String? = null,
    @SerializedName("chanceofwindy")
    var chanceofwindy: String? = null,
    @SerializedName("cloudcover")
    var cloudcover: String? = null,
    @SerializedName("humidity")
    var humidity: String? = null,
    @SerializedName("lang_vi")
    var langVi: List<LangViResponse>? = null,
    @SerializedName("precipInches")
    var precipInches: String? = null,
    @SerializedName("precipMM")
    var precipMM: String? = null,
    @SerializedName("pressure")
    var pressure: String? = null,
    @SerializedName("pressureInches")
    var pressureInches: String? = null,
    @SerializedName("tempC")
    var tempC: String? = null,
    @SerializedName("tempF")
    var tempF: String? = null,
    @SerializedName("time")
    var time: String? = null,
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
) : MapAbleToModel<HourlyModel> {
    override fun toModel(): HourlyModel {
        return HourlyModel(
            dewPointC = dewPointC,
            dewPointF = dewPointF,
            feelsLikeC = feelsLikeC,
            feelsLikeF = feelsLikeF,
            heatIndexC = heatIndexC,
            heatIndexF = heatIndexF,
            windChillC = windChillC,
            windChillF = windChillF,
            windGustKmph = windGustKmph,
            windGustMiles = windGustMiles,
            chanceoffog = chanceoffog,
            chanceoffrost = chanceoffrost,
            chanceofhightemp = chanceofhightemp,
            chanceofovercast = chanceofovercast,
            chanceofrain = chanceofrain,
            chanceofremdry = chanceofremdry,
            chanceofsnow = chanceofsnow,
            chanceofsunshine = chanceofsunshine,
            chanceofthunder = chanceofthunder,
            chanceofwindy = chanceofwindy,
            cloudcover = cloudcover,
            humidity = humidity,
            langVi = langVi?.map { it.toModel() },
            precipInches = precipInches,
            precipMM = precipMM,
            pressure = pressure,
            pressureInches = pressureInches,
            tempC = tempC,
            tempF = tempF,
            time = time,
            uvIndex = uvIndex,
            visibility = visibility,
            visibilityMiles = visibilityMiles,
            weatherCode = weatherCode,
            weatherIconUrl = weatherIconUrl?.map { it.toModel() },
            weatherDesc = weatherDesc?.map { it.toModel() },
            winddir16Point = winddir16Point,
            winddirDegree = winddirDegree,
            windspeedKmph = windspeedKmph,
            windspeedMiles = windspeedMiles,
        )
    }

}
