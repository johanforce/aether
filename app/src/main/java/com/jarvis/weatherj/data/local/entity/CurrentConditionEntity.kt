package com.jarvis.weatherj.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current")
data class CurrentConditionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "feels_like_c")
    val feelsLikeC: String? = null,
    @ColumnInfo(name = "feels_like_f")
    val feelsLikeF: String? = null,
    @ColumnInfo(name = "cloudcover")
    val cloudcover: String? = null,
    @ColumnInfo(name = "humidity")
    val humidity: String? = null,
    @ColumnInfo(name = "local_obs_date_time")
    val localObsDateTime: String? = null,
    @ColumnInfo(name = "observation_time")
    val observationTime: String? = null,
    @ColumnInfo(name = "precip_inches")
    val precipInches: String? = null,
    @ColumnInfo(name = "precip_mm")
    val precipMM: String? = null,
    @ColumnInfo(name = "pressure")
    val pressure: String? = null,
    @ColumnInfo(name = "pressure_inches")
    val pressureInches: String? = null,
    @ColumnInfo(name = "temp_c")
    val tempC: String? = null,
    @ColumnInfo(name = "temp_f")
    val tempF: String? = null,
    @ColumnInfo(name = "uv_index")
    val uvIndex: String? = null,
    @ColumnInfo(name = "visibility")
    val visibility: String? = null,
    @ColumnInfo(name = "visibility_miles")
    val visibilityMiles: String? = null,
    @ColumnInfo(name = "weather_code")
    val weatherCode: String? = null,
    @ColumnInfo(name = "winddir_16_point")
    val winddir16Point: String? = null,
    @ColumnInfo(name = "winddir_degree")
    val winddirDegree: String? = null,
    @ColumnInfo(name = "windspeed_kmph")
    val windspeedKmph: String? = null,
    @ColumnInfo(name = "windspeed_miles")
    val windspeedMiles: String? = null
)
