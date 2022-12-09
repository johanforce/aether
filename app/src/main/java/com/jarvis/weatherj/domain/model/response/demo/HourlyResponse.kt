package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

data class HourlyResponse(
    @SerializedName("DewPointC")
    var DewPointC: String? = null,
    @SerializedName("DewPointF")
    var DewPointF: String? = null,
    @SerializedName("FeelsLikeC")
    var FeelsLikeC: String? = null,
    @SerializedName("FeelsLikeF")
    var FeelsLikeF: String? = null,
    @SerializedName("HeatIndexC")
    var HeatIndexC: String? = null,
    @SerializedName("HeatIndexF")
    var HeatIndexF: String? = null,
    @SerializedName("WindChillC")
    var WindChillC: String? = null,
    @SerializedName("WindChillF")
    var WindChillF: String? = null,
    @SerializedName("WindGustKmph")
    var WindGustKmph: String? = null,
    @SerializedName("WindGustMiles")
    var WindGustMiles: String? = null,
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
    var lang_vi: List<LangViResponse>? = null,
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
)