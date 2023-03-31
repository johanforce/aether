package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.AstronomyResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class AstronomyModel(
    var moonIllumination: String? = null,
    var moonPhase: String? = null,
    var moonrise: String? = null,
    var moonset: String? = null,
    var sunrise: String? = null,
    var sunset: String? = null
): Parcelable{
    companion object {
        fun convertFromEntity(entity: AstronomyResponse): AstronomyModel {
            val model = AstronomyModel()
            model.apply {
                moonIllumination = entity.moon_illumination
                moonPhase = entity.moon_phase
                moonrise = entity.moonrise
                moonset = entity.moonset
                sunrise = entity.sunrise
                sunset = entity.sunset
            }
            return model
        }

        fun convertFromEntity(entity: List<AstronomyResponse>): List<AstronomyModel> {
            return entity.map {
                val model = AstronomyModel()
                model.apply {
                    moonIllumination = it.moon_illumination
                    moonPhase = it.moon_phase
                    moonrise = it.moonrise
                    moonset = it.moonset
                    sunrise = it.sunrise
                    sunset = it.sunset
                }
            }
        }
    }
}