package com.jarvis.weatherj.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current")
data class CurrentConditionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "FeelsLikeC")
    val FeelsLikeC: String? = null,
    @ColumnInfo(name = "FeelsLikeF")
    val FeelsLikeF: String? = null,
    @ColumnInfo(name = "cloudcover")
    val cloudcover: String? = null,
    @ColumnInfo(name = "humidity")
    val humidity: String? = null,
    @ColumnInfo(name = "localObsDateTime")
    val localObsDateTime: String? = null,
    @ColumnInfo(name = "observation_time")
    val observation_time: String? = null,
    @ColumnInfo(name = "precipInches")
    val precipInches: String? = null,
    @ColumnInfo(name = "precipMM")
    val precipMM: String? = null,
    @ColumnInfo(name = "pressure")
    val pressure: String? = null,
    @ColumnInfo(name = "pressureInches")
    val pressureInches: String? = null,
    @ColumnInfo(name = "temp_C")
    val temp_C: String? = null,
    @ColumnInfo(name = "temp_F")
    val temp_F: String? = null,
    @ColumnInfo(name = "uvIndex")
    val uvIndex: String? = null,
    @ColumnInfo(name = "visibility")
    val visibility: String? = null,
    @ColumnInfo(name = "visibilityMiles")
    val visibilityMiles: String? = null,
    @ColumnInfo(name = "weatherCode")
    val weatherCode: String? = null,
    @ColumnInfo(name = "winddir16Point")
    val winddir16Point: String? = null,
    @ColumnInfo(name = "winddirDegree")
    val winddirDegree: String? = null,
    @ColumnInfo(name = "windspeedKmph")
    val windspeedKmph: String? = null,
    @ColumnInfo(name = "windspeedMiles")
    val windspeedMiles: String? = null
)