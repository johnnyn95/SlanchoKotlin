package com.example.slancho.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.slancho.db.model.Forecast
import org.jetbrains.annotations.NotNull

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: Forecast)

    @Query("SELECT * FROM forecast WHERE cityName =:cityName")
    fun getLatestForecastByCityName(cityName: String): Forecast

    @Query("SELECT * FROM forecast WHERE cityId =:cityId")
    fun getLatestForecastByCityId(cityId: Long): Forecast
}