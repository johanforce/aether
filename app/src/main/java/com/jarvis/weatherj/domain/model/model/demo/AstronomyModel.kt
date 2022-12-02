package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.response.demo.AstronomyResponse
import com.jarvis.weatherj.domain.model.response.demo.WeatherDescResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AstronomyModel(
    var moon_illumination: String? = null,
    var moon_phase: String? = null,
    var moonrise: String? = null,
    var moonset: String? = null,
    var sunrise: String? = null,
    var sunset: String? = null
): Parcelable{
    companion object {
        fun convertFromEntity(entity: AstronomyResponse): AstronomyModel {
            val model = AstronomyModel()
            model.apply {
                moon_illumination = entity.moon_illumination
                moon_phase = entity.moon_phase
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
                    moon_illumination = it.moon_illumination
                    moon_phase = it.moon_phase
                    moonrise = it.moonrise
                    moonset = it.moonset
                    sunrise = it.sunrise
                    sunset = it.sunset
                }
            }
        }
    }
}