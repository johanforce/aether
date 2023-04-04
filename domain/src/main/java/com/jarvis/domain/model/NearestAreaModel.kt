package com.jarvis.domain.model

import android.os.Parcelable
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
) : Parcelable

