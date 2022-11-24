package com.jarvis.weatherj.domain.model.model

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity
import com.jarvis.weatherj.domain.model.response.CurrentConditionResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentConditionModel(
    val FeelsLikeC: String,
    val FeelsLikeF: String,
    val cloudcover: String,
    val humidity: String,
    val localObsDateTime: String,
    val observation_time: String,
    val precipInches: String,
    val precipMM: String,
    val pressure: String,
    val pressureInches: String,
    val temp_C: String,
    val temp_F: String,
    val uvIndex: String,
    val visibility: String,
    val visibilityMiles: String,
    val weatherCode: String,
    val winddir16Point: String,
    val winddirDegree: String,
    val windspeedKmph: String,
    val windspeedMiles: String
) : Parcelable {
    companion object {
        fun convertEntityToModel(entity: CurrentConditionEntity?): CurrentConditionModel? {
            if (entity == null) return null
            return CurrentConditionModel(
                FeelsLikeC = entity.FeelsLikeC,
                FeelsLikeF = entity.FeelsLikeF,
                cloudcover = entity.cloudcover,
                humidity = entity.humidity,
                localObsDateTime = entity.localObsDateTime,
                observation_time = entity.observation_time,
                precipInches = entity.precipInches,
                precipMM = entity.precipMM,
                pressure = entity.pressure,
                pressureInches = entity.pressureInches,
                temp_C = entity.temp_C,
                temp_F = entity.temp_F,
                uvIndex = entity.uvIndex,
                visibility = entity.visibility,
                visibilityMiles = entity.visibilityMiles,
                weatherCode = entity.weatherCode,
                winddir16Point = entity.winddir16Point,
                winddirDegree = entity.winddirDegree,
                windspeedKmph = entity.windspeedKmph,
                windspeedMiles = entity.windspeedMiles,
            )
        }

        fun convertModelToEntity(model: CurrentConditionModel): CurrentConditionEntity {
            return CurrentConditionEntity(
                FeelsLikeC = model.FeelsLikeC,
                FeelsLikeF = model.FeelsLikeF,
                cloudcover = model.cloudcover,
                humidity = model.humidity,
                localObsDateTime = model.localObsDateTime,
                observation_time = model.observation_time,
                precipInches = model.precipInches,
                precipMM = model.precipMM,
                pressure = model.pressure,
                pressureInches = model.pressureInches,
                temp_C = model.temp_C,
                temp_F = model.temp_F,
                uvIndex = model.uvIndex,
                visibility = model.visibility,
                visibilityMiles = model.visibilityMiles,
                weatherCode = model.weatherCode,
                winddir16Point = model.winddir16Point,
                winddirDegree = model.winddirDegree,
                windspeedKmph = model.windspeedKmph,
                windspeedMiles = model.windspeedMiles,
            )
        }


        fun convertResponseToModel(model: CurrentConditionResponse): CurrentConditionModel {
            return CurrentConditionModel(
                FeelsLikeC = model.FeelsLikeC ?: "",
                FeelsLikeF = model.FeelsLikeF ?: "",
                cloudcover = model.cloudcover ?: "",
                humidity = model.humidity ?: "",
                localObsDateTime = model.localObsDateTime ?: "",
                observation_time = model.observation_time ?: "",
                precipInches = model.precipInches ?: "",
                precipMM = model.precipMM ?: "",
                pressure = model.pressure ?: "",
                pressureInches = model.pressureInches ?: "",
                temp_C = model.temp_C ?: "",
                temp_F = model.temp_F ?: "",
                uvIndex = model.uvIndex ?: "",
                visibility = model.visibility ?: "",
                visibilityMiles = model.visibilityMiles ?: "",
                weatherCode = model.weatherCode ?: "",
                winddir16Point = model.winddir16Point ?: "",
                winddirDegree = model.winddirDegree ?: "",
                windspeedKmph = model.windspeedKmph ?: "",
                windspeedMiles = model.windspeedMiles ?: "",
            )
        }
    }
}
