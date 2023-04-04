package com.jarvis.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LangViModel(
    var value: String? = null
) : Parcelable
