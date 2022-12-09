package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.domain.model.response.demo.WeatherResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    var astronomy: List<AstronomyModel>? = null,
    var avgtempC: String? = null,
    var avgtempF: String? = null,
    var date: String? = null,
    var hourly: List<HourlyModel>? = null,
    var maxtempC: String? = null,
    var maxtempF: String? = null,
    var mintempC: String? = null,
    var mintempF: String? = null,
    var sunHour: String? = null,
    var totalSnow_cm: String? = null,
    var uvIndex: String? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: WeatherResponse): WeatherModel {
            val model = WeatherModel()
            model.apply {
                astronomy = AstronomyModel.convertFromEntity(entity.astronomy ?: emptyList())
                avgtempC = entity.avgtempC
                avgtempF = entity.avgtempF
                date = entity.date
                hourly = HourlyModel.convertFromEntity(entity.hourly ?: emptyList())
                maxtempC = entity.maxtempC
                maxtempF = entity.maxtempF
                mintempC = entity.mintempC
                mintempF = entity.mintempF
                sunHour = entity.sunHour
                totalSnow_cm = entity.totalSnow_cm
                uvIndex = entity.uvIndex
            }
            return model
        }

        fun convertFromEntity(entity: List<WeatherResponse>): List<WeatherModel> {
            return entity.map {
                val model = WeatherModel()
                model.apply {
                    astronomy = AstronomyModel.convertFromEntity(it.astronomy ?: emptyList())
                    avgtempC = it.avgtempC
                    avgtempF = it.avgtempF
                    date = it.date
                    hourly = HourlyModel.convertFromEntity(it.hourly ?: emptyList())
                    maxtempC = it.maxtempC
                    maxtempF = it.maxtempF
                    mintempC = it.mintempC
                    mintempF = it.mintempF
                    sunHour = it.sunHour
                    totalSnow_cm = it.totalSnow_cm
                    uvIndex = it.uvIndex
                }
            }
        }
    }
}