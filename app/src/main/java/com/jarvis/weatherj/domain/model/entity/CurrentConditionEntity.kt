package com.jarvis.weatherj.domain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrentConditionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "FeelsLikeC")
    val FeelsLikeC: String,
    @ColumnInfo(name = "FeelsLikeF")
    val FeelsLikeF: String,
    @ColumnInfo(name = "cloudcover")
    val cloudcover: String,
    @ColumnInfo(name = "humidity")
    val humidity: String,
    @ColumnInfo(name = "localObsDateTime")
    val localObsDateTime: String,
    @ColumnInfo(name = "observation_time")
    val observation_time: String,
    @ColumnInfo(name = "precipInches")
    val precipInches: String,
    @ColumnInfo(name = "precipMM")
    val precipMM: String,
    @ColumnInfo(name = "pressure")
    val pressure: String,
    @ColumnInfo(name = "pressureInches")
    val pressureInches: String,
    @ColumnInfo(name = "temp_C")
    val temp_C: String,
    @ColumnInfo(name = "temp_F")
    val temp_F: String,
    @ColumnInfo(name = "uvIndex")
    val uvIndex: String,
    @ColumnInfo(name = "visibility")
    val visibility: String,
    @ColumnInfo(name = "visibilityMiles")
    val visibilityMiles: String,
    @ColumnInfo(name = "weatherCode")
    val weatherCode: String,
    @ColumnInfo(name = "winddir16Point")
    val winddir16Point: String,
    @ColumnInfo(name = "winddirDegree")
    val winddirDegree: String,
    @ColumnInfo(name = "windspeedKmph")
    val windspeedKmph: String,
    @ColumnInfo(name = "windspeedMiles")
    val windspeedMiles: String
)