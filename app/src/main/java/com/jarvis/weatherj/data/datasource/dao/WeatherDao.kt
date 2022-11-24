package com.jarvis.weatherj.data.datasource.dao

import androidx.room.*
import com.jarvis.weatherj.domain.model.entity.CurrentConditionEntity

@Dao
interface WeatherDao {
    @Query("select * from currentconditionentity where id=:id")
    suspend fun getWeatherById(id: Int): CurrentConditionEntity?
}
