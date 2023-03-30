package com.jarvis.weatherj.data.remote.response

import com.google.gson.annotations.SerializedName
import com.jarvis.weatherj.domain.mapper.MapAbleToModel
import com.jarvis.weatherj.domain.model.model.demo.NearestAreaModel

data class NearestAreaResponse(
    @SerializedName("areaName")
    var areaName: List<AreaNameResponse>? = null,
    @SerializedName("country")
    var country: List<CountryResponse>? = null,
    @SerializedName("latitude")
    var latitude: String? = null,
    @SerializedName("longitude")
    var longitude: String? = null,
    @SerializedName("population")
    var population: String? = null,
    @SerializedName("region")
    var region: List<RegionResponse>? = null,
    @SerializedName("weatherUrl")
    var weatherUrl: List<WeatherUrlResponse>? = null
) : MapAbleToModel<NearestAreaModel> {
    override fun toModel(): NearestAreaModel {
        return NearestAreaModel(
            areaName = areaName?.map { it.toModel() },
            country = country?.map { it.toModel() },
            latitude = latitude,
            longitude = longitude,
            population = population,
            region = region?.map { it.toModel() },
            weatherUrl = weatherUrl?.map { it.toModel() }
        )
    }

}