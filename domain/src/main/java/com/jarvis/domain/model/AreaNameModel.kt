package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AreaNameModel(
    var value: String? = null
) : Parcelable
