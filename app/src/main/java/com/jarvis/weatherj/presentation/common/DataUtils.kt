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

    fun convertWindDirToWind(windDir: String): String{
        var wind = when(windDir){
            WindEnum.N.wind -> WindEnum.N.Data
            WindEnum.NNE.wind -> WindEnum.NNE.Data
            WindEnum.NE.wind -> WindEnum.NE.Data
            WindEnum.ENE.wind -> WindEnum.ENE.Data
            WindEnum.E.wind -> WindEnum.E.Data
            WindEnum.ESE.wind -> WindEnum.ESE.Data
            WindEnum.SE.wind -> WindEnum.SE.Data
            WindEnum.SSE.wind -> WindEnum.SSE.Data
            WindEnum.S.wind -> WindEnum.S.Data
            WindEnum.SSW.wind -> WindEnum.SSW.Data
            WindEnum.SW.wind -> WindEnum.SW.Data
            WindEnum.WSW.wind -> WindEnum.WSW.Data
            WindEnum.W.wind -> WindEnum.W.Data
            WindEnum.WNW.wind -> WindEnum.WNW.Data
            WindEnum.NW.wind -> WindEnum.NW.Data
            else -> WindEnum.NNW.Data
        }
        return wind
    }
}