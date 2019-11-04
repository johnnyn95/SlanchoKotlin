package com.example.slancho.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.slancho.db.model.Forecast

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: Forecast)

    @Query("SELECT * FROM forecast WHERE cityName =:cityName ORDER BY dateTimeOfFetch DESC LIMIT 1")
    fun getLatestForecastByCityName(cityName: String): Forecast

    @Query("SELECT * FROM forecast WHERE cityId =:cityId ORDER BY dateTimeOfFetch DESC LIMIT 1")
    fun getLatestForecastByCityId(cityId: Long): Forecast
}