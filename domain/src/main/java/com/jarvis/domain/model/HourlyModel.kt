package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyModel(
    var dewPointC: String? = null,
    var dewPointF: String? = null,
    var feelsLikeC: String? = null,
    var feelsLikeF: String? = null,
    var heatIndexC: String? = null,
    var heatIndexF: String? = null,
    var windChillC: String? = null,
    var windChillF: String? = null,
    var windGustKmph: String? = null,
    var windGustMiles: String? = null,
    var chanceoffog: String? = null,
    var chanceoffrost: String? = null,
    var chanceofhightemp: String? = null,
    var chanceofovercast: String? = null,
    var chanceofrain: String? = null,
    var chanceofremdry: String? = null,
    var chanceofsnow: String? = null,
    var chanceofsunshine: String? = null,
    var chanceofthunder: String? = null,
    var chanceofwindy: String? = null,
    var cloudcover: String? = null,
    var humidity: String? = null,
    var langVi: List<LangViModel>? = null,
    var precipInches: String? = null,
    var precipMM: String? = null,
    var pressure: String? = null,
    var pressureInches: String? = null,
    var tempC: String? = null,
    var tempF: String? = null,
    var time: String? = null,
    var uvIndex: String? = null,
    var visibility: String? = null,
    var visibilityMiles: String? = null,
    var weatherCode: String? = null,
    var weatherDesc: List<WeatherDescModel>? = null,
    var weatherIconUrl: List<WeatherIconUrlModel>? = null,
    var winddir16Point: String? = null,
    var winddirDegree: String? = null,
    var windspeedKmph: String? = null,
    var windspeedMiles: String? = null
) : Parcelable
