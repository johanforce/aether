package com.jarvis.weatherj.domain.model.model.demo

import android.os.Parcelable
import com.jarvis.weatherj.data.remote.response.NearestAreaResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class NearestAreaModel(
    var areaName: List<AreaNameModel>? = null,
    var country: List<CountryModels>? = null,
    var latitude: String? = null,
    var longitude: String? = null,
    var population: String? = null,
    var region: List<RegionModel>? = null,
    var weatherUrl: List<WeatherUrlModel>? = null
) : Parcelable {
    companion object {
        fun convertFromEntity(entity: List<NearestAreaResponse>): List<NearestAreaModel> {
            return entity.map {
                val model = NearestAreaModel()
                model.apply {
                    areaName = AreaNameModel.convertFromEntity(it.areaName ?: emptyList())
                    country = CountryModels.convertFromEntity(it.country ?: emptyList())
                    latitude = it.latitude
                    longitude = it.longitude
                    population = it.population
                    region = RegionModel.convertFromEntity(it.region ?: emptyList())
                    weatherUrl = WeatherUrlModel.convertFromEntity(it.weatherUrl ?: emptyList())
                }
            }
        }
    }
}