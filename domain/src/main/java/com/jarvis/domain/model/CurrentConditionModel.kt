package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentConditionModel(
    var feelsLikeC: String? = null,
    var feelsLikeF: String? = null,
    var cloudcover: String? = null,
    var humidity: String? = null,
    var langVi: List<LangViModel>? = null,
    var localObsDateTime: String? = null,
    var observationTime: String? = null,
    var precipInches: String? = null,
    var precipMM: String? = null,
    var pressure: String? = null,
    var pressureInches: String? = null,
    var tempC: String? = null,
    var tempF: String? = null,
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
