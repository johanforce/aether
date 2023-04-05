@file:Suppress("MemberVisibilityCanBePrivate")

package com.jarvis.weatherj.presentation.common

import android.content.Context
import com.jarvis.weatherj.R
import java.text.SimpleDateFormat
import java.util.*

object DataUtils {
    const val ISO_8601_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm"

    private fun getDateUpdateTimeFormat(pattern: String = ISO_8601_DATE_TIME_FORMAT): SimpleDateFormat {
        return SimpleDateFormat(pattern, Locale.getDefault())
    }

    fun Long.toTimeShowUI(format: String): String {
        return getDateUpdateTimeFormat(format)
            .format(this)
    }

    fun convertIndexUV(context: Context, data: String): String {
        return try {
            when (data.toDouble()) {
                in 0.0..2.0 -> context.getString(R.string.uv_index_low)
                in 2.0..5.0 -> context.getString(R.string.uv_index_medium)
                in 5.0..11.0 -> context.getString(R.string.uv_index_high)
                else -> context.getString(R.string.uv_index_dangerous)
            }
        } catch (e: Exception) {
            ""
        }
    }

    fun convertWindDirToWind(windDir: String): Int {
        return WindEnum.values().find { it.wind == windDir }?.data ?: WindEnum.NNW.data
    }

    fun convertImageWeather(code: Int): Int {
        return WeatherEnum.values().find { it.code == code }?.image
            ?: WeatherEnum.HeavySnowShowers.image
    }

    fun convertTitleWeather(code: Int): Int {
        return WeatherEnum.values().find { it.code == code }?.nameWeather
            ?: WeatherEnum.HeavySnowShowers.nameWeather
    }

    fun convertTimeToString(time: String): String {
        if (time == "0") {
            return "0:00"
        }

        return time.replace("00", ":00")
    }

    const val FORMAT_DATE_DD_MM = "dd/MM"

    fun formatDateToString(source: Date?, format: String?): String? {
        if (source == null) {
            return null
        }
        if (format == null || format.isEmpty()) {
            return null
        }
        val sdf = getDateFormat(format)
        return sdf.format(source)
    }

    fun getDateFormat(pattern: String = ISO_8601_DATE_TIME_FORMAT): SimpleDateFormat {
        return SimpleDateFormat(pattern, getLocale())
    }

    private fun getLocale(): Locale {
        return Locale.ENGLISH
    }

}
