package com.example.slancho.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.slancho.db.model.ForecastInfo

@Dao
interface ForecastInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastInfo: ForecastInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastInfoList(vararg forecastInfoList: ForecastInfo)

    @Query("SELECT * FROM forecastInfo WHERE forecastId =:forecastId")
    fun getLatestForecastDataListByForecastId(forecastId: String): Array<ForecastInfo>
}