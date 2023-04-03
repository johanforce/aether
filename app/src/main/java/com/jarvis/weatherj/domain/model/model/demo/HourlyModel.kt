package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.HourlyResponse
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
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: HourlyResponse): HourlyModel {
            val model = HourlyModel()
            model.apply {
                dewPointC = entity.dewPointC
                dewPointF = entity.dewPointF
                feelsLikeC = entity.feelsLikeC
                feelsLikeF = entity.feelsLikeF
                heatIndexC = entity.heatIndexC
                heatIndexF = entity.heatIndexF
                windChillC = entity.windChillC
                windChillF = entity.windChillF
                windGustKmph = entity.windGustKmph
                windGustMiles = entity.windGustMiles
                chanceoffog = entity.chanceoffog
                chanceoffrost = entity.chanceoffrost
                chanceofhightemp = entity.chanceofhightemp
                chanceofovercast = entity.chanceofovercast
                chanceofrain = entity.chanceofrain
                chanceofremdry = entity.chanceofremdry
                chanceofsnow = entity.chanceofsnow
                chanceofsunshine = entity.chanceofsunshine
                chanceofthunder = entity.chanceofthunder
                chanceofwindy = entity.chanceofwindy
                cloudcover = entity.cloudcover
                humidity = entity.humidity
                langVi = LangViModel.convertFromEntity(entity.langVi ?: emptyList())
                precipInches = entity.precipInches
                precipMM = entity.precipMM
                pressure = entity.pressure
                pressureInches = entity.pressureInches
                tempC = entity.tempC
                tempF = entity.tempF
                time = entity.time
                uvIndex = entity.uvIndex
                visibility = entity.visibility
                visibilityMiles = entity.visibilityMiles
                weatherCode = entity.weatherCode
                weatherIconUrl =
                    WeatherIconUrlModel.convertFromEntity(entity.weatherIconUrl ?: emptyList())
                weatherDesc = WeatherDescModel.convertFromEntity(entity.weatherDesc ?: emptyList())
                winddir16Point = entity.winddir16Point
                winddirDegree = entity.winddirDegree
                windspeedKmph = entity.windspeedKmph
                windspeedMiles = entity.windspeedMiles
            }
            return model
        }

        fun convertFromEntity(entity: List<HourlyResponse>): List<HourlyModel> {
            return entity.map {
                val model = HourlyModel()
                model.apply {
                    dewPointC = it.dewPointC
                    dewPointF = it.dewPointF
                    feelsLikeC = it.feelsLikeC
                    feelsLikeF = it.feelsLikeF
                    heatIndexC = it.heatIndexC
                    heatIndexF = it.heatIndexF
                    windChillC = it.windChillC
                    windChillF = it.windChillF
                    windGustKmph = it.windGustKmph
                    windGustMiles = it.windGustMiles
                    chanceoffog = it.chanceoffog
                    chanceoffrost = it.chanceoffrost
                    chanceofhightemp = it.chanceofhightemp
                    chanceofovercast = it.chanceofovercast
                    chanceofrain = it.chanceofrain
                    chanceofremdry = it.chanceofremdry
                    chanceofsnow = it.chanceofsnow
                    chanceofsunshine = it.chanceofsunshine
                    chanceofthunder = it.chanceofthunder
                    chanceofwindy = it.chanceofwindy
                    cloudcover = it.cloudcover
                    humidity = it.humidity
                    langVi = LangViModel.convertFromEntity(it.langVi ?: emptyList())
                    precipInches = it.precipInches
                    precipMM = it.precipMM
                    pressure = it.pressure
                    pressureInches = it.pressureInches
                    tempC = it.tempC
                    tempF = it.tempF
                    time = it.time
                    uvIndex = it.uvIndex
                    visibility = it.visibility
                    visibilityMiles = it.visibilityMiles
                    weatherCode = it.weatherCode
                    weatherIconUrl =
                        WeatherIconUrlModel.convertFromEntity(it.weatherIconUrl ?: emptyList())
                    weatherDesc = WeatherDescModel.convertFromEntity(it.weatherDesc ?: emptyList())
                    winddir16Point = it.winddir16Point
                    winddirDegree = it.winddirDegree
                    windspeedKmph = it.windspeedKmph
                    windspeedMiles = it.windspeedMiles
                }
            }
        }
    }
}
