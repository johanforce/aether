@file:Suppress("unused")

package com.jarvis.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.jarvis.data.local.entity.CurrentConditionEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM current")
    suspend fun getAll(): List<CurrentConditionEntity>
}
