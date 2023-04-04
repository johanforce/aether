package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherIconUrlModel(
    var value: String? = null
) : Parcelable
