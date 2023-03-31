package com.jarvis.weatherj.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jarvis.weatherj.data.local.dao.WeatherDao
import com.jarvis.weatherj.data.local.AppDatabase.Companion.DATABASE_VERSION
import com.jarvis.weatherj.data.local.entity.CurrentConditionEntity

@Database(
    entities = [
        CurrentConditionEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion
    object {
        const val DATABASE_VERSION = 1
        const val DB_NAME = "db_app.db"

        fun build(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .createFromAsset(DB_NAME)
                .build()
    }
}
