package com.jarvis.weatherj.presentation.common

import android.content.Context
import com.jarvis.weatherj.R

object DataUtils {
    fun convertIndexUV(context: Context, data: String): String{
        return try{
            when (data.toDouble()) {
                in 0.0..2.0 -> context.getString(R.string.uv_index_low)
                in 2.0..5.0 -> context.getString(R.string.uv_index_medium)
                in 5.0..11.0 -> context.getString(R.string.uv_index_high)
                else -> context.getString(R.string.uv_index_dangerous)
            }
        }catch (e: Exception){
            ""
        }
    }
}