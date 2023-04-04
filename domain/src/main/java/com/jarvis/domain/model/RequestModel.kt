package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestModel(
    var query: String? = null,
    var type: String? = null
) : Parcelable
