package com.jarvis.weatherj.presentation.common

import android.content.Context
import com.jarvis.weatherj.MainApplication
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
        var wind = when (windDir) {
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

    fun convertImageWeather(code: Int): Int {
        var image = when (code) {
            WeatherEnum.Sunny.code -> WeatherEnum.Sunny.image
            WeatherEnum.Cloudy.code -> WeatherEnum.Cloudy.image
            WeatherEnum.PartlyCloudy.code -> WeatherEnum.PartlyCloudy.image
            WeatherEnum.VeryCloudy.code -> WeatherEnum.VeryCloudy.image
            WeatherEnum.Fog.code -> WeatherEnum.Fog.image
            WeatherEnum.LightShowers.code -> WeatherEnum.LightShowers.image
            WeatherEnum.LightSleetShowers.code -> WeatherEnum.LightSleetShowers.image
            WeatherEnum.LightSleet.code -> WeatherEnum.LightSleet.image
            WeatherEnum.LightSleet2.code -> WeatherEnum.LightSleet2.image
            WeatherEnum.ThunderyShowers.code -> WeatherEnum.ThunderyShowers.image
            WeatherEnum.LightSnow.code -> WeatherEnum.LightSnow.image
            WeatherEnum.HeavySnow.code -> WeatherEnum.HeavySnow.image
            WeatherEnum.Fog2.code -> WeatherEnum.Fog2.image
            WeatherEnum.Fog3.code -> WeatherEnum.Fog3.image
            WeatherEnum.LightShowers2.code -> WeatherEnum.LightShowers2.image
            WeatherEnum.LightRain.code -> WeatherEnum.LightRain.image
            WeatherEnum.LightSleet3.code -> WeatherEnum.LightSleet3.image
            WeatherEnum.LightSleet4.code -> WeatherEnum.LightSleet4.image
            WeatherEnum.LightRain2.code -> WeatherEnum.LightRain2.image
            WeatherEnum.LightRain3.code -> WeatherEnum.LightRain3.image
            WeatherEnum.HeavyShowers2.code -> WeatherEnum.HeavyShowers2.image
            WeatherEnum.HeavyRain2.code -> WeatherEnum.HeavyRain2.image
            WeatherEnum.HeavyShowers3.code -> WeatherEnum.HeavyShowers3.image
            WeatherEnum.HeavyRain3.code -> WeatherEnum.HeavyRain3.image
            WeatherEnum.LightSleet5.code -> WeatherEnum.LightSleet5.image
            WeatherEnum.LightSleet6.code -> WeatherEnum.LightSleet6.image
            WeatherEnum.LightSleet7.code -> WeatherEnum.LightSleet7.image
            WeatherEnum.LightSnow2.code -> WeatherEnum.LightSnow2.image
            WeatherEnum.LightSnowShowers2.code -> WeatherEnum.LightSnowShowers2.image
            WeatherEnum.LightSnowShowers3.code -> WeatherEnum.LightSnowShowers3.image
            WeatherEnum.HeavySnow2.code -> WeatherEnum.HeavySnow2.image
            WeatherEnum.HeavySnow3.code -> WeatherEnum.HeavySnow3.image
            WeatherEnum.HeavySnowShowers2.code -> WeatherEnum.HeavySnowShowers2.image
            WeatherEnum.HeavySnow4.code -> WeatherEnum.HeavySnow4.image
            WeatherEnum.LightSleet8.code -> WeatherEnum.LightSleet8.image
            WeatherEnum.LightShowers3.code -> WeatherEnum.LightShowers3.image
            WeatherEnum.HeavyShowers.code -> WeatherEnum.HeavyShowers.image
            WeatherEnum.HeavyRain.code -> WeatherEnum.HeavyRain.image
            WeatherEnum.LightSleetShowers2.code -> WeatherEnum.LightSleetShowers2.image
            WeatherEnum.LightSleetShowers3.code -> WeatherEnum.LightSleetShowers3.image
            WeatherEnum.LightSnowShowers.code -> WeatherEnum.LightSnowShowers.image
            WeatherEnum.HeavySnowShowers3.code -> WeatherEnum.HeavySnowShowers3.image
            WeatherEnum.LightSleetShowers4.code -> WeatherEnum.LightSleetShowers4.image
            WeatherEnum.LightSleet9.code -> WeatherEnum.LightSleet9.image
            WeatherEnum.ThunderyShowers2.code -> WeatherEnum.ThunderyShowers2.image
            WeatherEnum.ThunderyHeavyRain.code -> WeatherEnum.ThunderyHeavyRain.image
            WeatherEnum.ThunderySnowShowers.code -> WeatherEnum.ThunderySnowShowers.image
            WeatherEnum.HeavySnowShowers.code -> WeatherEnum.HeavySnowShowers.image
            else -> WeatherEnum.HeavySnowShowers.image
        }
        return image
    }

    fun convertTitleWeather(code: Int): Int {
        var image = when (code) {
            WeatherEnum.Sunny.code -> WeatherEnum.Sunny.nameWeather
            WeatherEnum.Cloudy.code -> WeatherEnum.Cloudy.nameWeather
            WeatherEnum.PartlyCloudy.code -> WeatherEnum.PartlyCloudy.nameWeather
            WeatherEnum.VeryCloudy.code -> WeatherEnum.VeryCloudy.nameWeather
            WeatherEnum.Fog.code -> WeatherEnum.Fog.nameWeather
            WeatherEnum.LightShowers.code -> WeatherEnum.LightShowers.nameWeather
            WeatherEnum.LightSleetShowers.code -> WeatherEnum.LightSleetShowers.nameWeather
            WeatherEnum.LightSleet.code -> WeatherEnum.LightSleet.nameWeather
            WeatherEnum.LightSleet2.code -> WeatherEnum.LightSleet2.nameWeather
            WeatherEnum.ThunderyShowers.code -> WeatherEnum.ThunderyShowers.nameWeather
            WeatherEnum.LightSnow.code -> WeatherEnum.LightSnow.nameWeather
            WeatherEnum.HeavySnow.code -> WeatherEnum.HeavySnow.nameWeather
            WeatherEnum.Fog2.code -> WeatherEnum.Fog2.nameWeather
            WeatherEnum.Fog3.code -> WeatherEnum.Fog3.nameWeather
            WeatherEnum.LightShowers2.code -> WeatherEnum.LightShowers2.nameWeather
            WeatherEnum.LightRain.code -> WeatherEnum.LightRain.nameWeather
            WeatherEnum.LightSleet3.code -> WeatherEnum.LightSleet3.nameWeather
            WeatherEnum.LightSleet4.code -> WeatherEnum.LightSleet4.nameWeather
            WeatherEnum.LightRain2.code -> WeatherEnum.LightRain2.nameWeather
            WeatherEnum.LightRain3.code -> WeatherEnum.LightRain3.nameWeather
            WeatherEnum.HeavyShowers2.code -> WeatherEnum.HeavyShowers2.nameWeather
            WeatherEnum.HeavyRain2.code -> WeatherEnum.HeavyRain2.nameWeather
            WeatherEnum.HeavyShowers3.code -> WeatherEnum.HeavyShowers3.nameWeather
            WeatherEnum.HeavyRain3.code -> WeatherEnum.HeavyRain3.nameWeather
            WeatherEnum.LightSleet5.code -> WeatherEnum.LightSleet5.nameWeather
            WeatherEnum.LightSleet6.code -> WeatherEnum.LightSleet6.nameWeather
            WeatherEnum.LightSleet7.code -> WeatherEnum.LightSleet7.nameWeather
            WeatherEnum.LightSnow2.code -> WeatherEnum.LightSnow2.nameWeather
            WeatherEnum.LightSnowShowers2.code -> WeatherEnum.LightSnowShowers2.nameWeather
            WeatherEnum.LightSnowShowers3.code -> WeatherEnum.LightSnowShowers3.nameWeather
            WeatherEnum.HeavySnow2.code -> WeatherEnum.HeavySnow2.nameWeather
            WeatherEnum.HeavySnow3.code -> WeatherEnum.HeavySnow3.nameWeather
            WeatherEnum.HeavySnowShowers2.code -> WeatherEnum.HeavySnowShowers2.nameWeather
            WeatherEnum.HeavySnow4.code -> WeatherEnum.HeavySnow4.nameWeather
            WeatherEnum.LightSleet8.code -> WeatherEnum.LightSleet8.nameWeather
            WeatherEnum.LightShowers3.code -> WeatherEnum.LightShowers3.nameWeather
            WeatherEnum.HeavyShowers.code -> WeatherEnum.HeavyShowers.nameWeather
            WeatherEnum.HeavyRain.code -> WeatherEnum.HeavyRain.nameWeather
            WeatherEnum.LightSleetShowers2.code -> WeatherEnum.LightSleetShowers2.nameWeather
            WeatherEnum.LightSleetShowers3.code -> WeatherEnum.LightSleetShowers3.nameWeather
            WeatherEnum.LightSnowShowers.code -> WeatherEnum.LightSnowShowers.nameWeather
            WeatherEnum.HeavySnowShowers3.code -> WeatherEnum.HeavySnowShowers3.nameWeather
            WeatherEnum.LightSleetShowers4.code -> WeatherEnum.LightSleetShowers4.nameWeather
            WeatherEnum.LightSleet9.code -> WeatherEnum.LightSleet9.nameWeather
            WeatherEnum.ThunderyShowers2.code -> WeatherEnum.ThunderyShowers2.nameWeather
            WeatherEnum.ThunderyHeavyRain.code -> WeatherEnum.ThunderyHeavyRain.nameWeather
            WeatherEnum.ThunderySnowShowers.code -> WeatherEnum.ThunderySnowShowers.nameWeather
            WeatherEnum.HeavySnowShowers.code -> WeatherEnum.HeavySnowShowers.nameWeather
            else -> WeatherEnum.HeavySnowShowers.nameWeather
        }
        return image
    }

    fun converTimeToString(time: String): String{
        if(time == "0"){
            return "0:00"
        }

        val revesTimeString = time.replace("00", ":00")
        return revesTimeString
    }

    const val FORMAT_DATE_DD_MM= "dd/MM"

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