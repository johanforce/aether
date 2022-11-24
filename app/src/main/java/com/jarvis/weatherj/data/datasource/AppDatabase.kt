package com.jarvis.weatherj.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jarvis.weatherj.data.datasource.dao.WeatherDao
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity

@Database(
    entities = [CurrentConditionEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        const val DATABASE_NAME = "weather_database"
    }
}
