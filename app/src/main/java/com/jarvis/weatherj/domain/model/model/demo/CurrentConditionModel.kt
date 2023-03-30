package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.CurrentConditionResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentConditionModel(
    var FeelsLikeC: String? = null,
    var FeelsLikeF: String? = null,
    var cloudcover: String? = null,
    var humidity: String? = null,
    var lang_vi: List<LangViModel>? = null,
    var localObsDateTime: String? = null,
    var observation_time: String? = null,
    var precipInches: String? = null,
    var precipMM: String? = null,
    var pressure: String? = null,
    var pressureInches: String? = null,
    var temp_C: String? = null,
    var temp_F: String? = null,
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
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: CurrentConditionResponse): CurrentConditionModel {
            val model = CurrentConditionModel()
            model.apply {
                FeelsLikeC = entity.FeelsLikeC
                FeelsLikeF = entity.FeelsLikeF
                cloudcover = entity.cloudcover
                humidity = entity.humidity
                lang_vi = LangViModel.convertFromEntity(entity.lang_vi ?: emptyList())
                localObsDateTime = entity.localObsDateTime
                observation_time = entity.observation_time
                precipInches = entity.precipInches
                precipMM = entity.precipMM
                pressure = entity.pressure
                pressureInches = entity.pressureInches
                temp_C = entity.temp_C
                temp_F = entity.temp_F
                uvIndex = entity.uvIndex
                visibility = entity.visibility
                visibilityMiles = entity.visibilityMiles
                weatherCode = entity.weatherCode
                weatherDesc = WeatherDescModel.convertFromEntity(entity.weatherDesc ?: emptyList())
                weatherIconUrl =
                    WeatherIconUrlModel.convertFromEntity(entity.weatherIconUrl ?: emptyList())
                winddir16Point = entity.winddir16Point
                winddirDegree = entity.winddirDegree
                windspeedKmph = entity.windspeedKmph
                windspeedMiles = entity.windspeedMiles
            }
            return model
        }

        fun convertFromEntity(entity: List<CurrentConditionResponse>): List<CurrentConditionModel> {
            return entity.map {
                val model = CurrentConditionModel()
                model.apply {
                    FeelsLikeC = it.FeelsLikeC
                    FeelsLikeF = it.FeelsLikeF
                    cloudcover = it.cloudcover
                    humidity = it.humidity
                    lang_vi = LangViModel.convertFromEntity(it.lang_vi ?: emptyList())
                    localObsDateTime = it.localObsDateTime
                    observation_time = it.observation_time
                    precipInches = it.precipInches
                    precipMM = it.precipMM
                    pressure = it.pressure
                    pressureInches = it.pressureInches
                    temp_C = it.temp_C
                    temp_F = it.temp_F
                    uvIndex = it.uvIndex
                    visibility = it.visibility
                    visibilityMiles = it.visibilityMiles
                    weatherCode = it.weatherCode
                    weatherDesc = WeatherDescModel.convertFromEntity(it.weatherDesc ?: emptyList())
                    weatherIconUrl =
                        WeatherIconUrlModel.convertFromEntity(it.weatherIconUrl ?: emptyList())
                    winddir16Point = it.winddir16Point
                    winddirDegree = it.winddirDegree
                    windspeedKmph = it.windspeedKmph
                    windspeedMiles = it.windspeedMiles
                }
            }
        }
    }
}