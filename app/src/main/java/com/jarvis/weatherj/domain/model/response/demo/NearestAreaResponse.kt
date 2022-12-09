package com.jarvis.weatherj.domain.model.response.demo

import com.google.gson.annotations.SerializedName

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
)