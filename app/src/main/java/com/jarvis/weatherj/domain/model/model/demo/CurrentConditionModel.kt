package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.CurrentConditionResponse
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
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: CurrentConditionResponse): CurrentConditionModel {
            val model = CurrentConditionModel()
            model.apply {
                feelsLikeC = entity.feelsLikeC
                feelsLikeF = entity.feelsLikeF
                cloudcover = entity.cloudcover
                humidity = entity.humidity
                langVi = LangViModel.convertFromEntity(entity.langVi ?: emptyList())
                localObsDateTime = entity.localObsDateTime
                observationTime = entity.observationTime
                precipInches = entity.precipInches
                precipMM = entity.precipMM
                pressure = entity.pressure
                pressureInches = entity.pressureInches
                tempC = entity.tempC
                tempF = entity.tempF
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
                    feelsLikeC = it.feelsLikeC
                    feelsLikeF = it.feelsLikeF
                    cloudcover = it.cloudcover
                    humidity = it.humidity
                    langVi = LangViModel.convertFromEntity(it.langVi ?: emptyList())
                    localObsDateTime = it.localObsDateTime
                    observationTime = it.observationTime
                    precipInches = it.precipInches
                    precipMM = it.precipMM
                    pressure = it.pressure
                    pressureInches = it.pressureInches
                    tempC = it.tempC
                    tempF = it.tempF
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
