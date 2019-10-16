package com.example.slancho.db.dao

import androidx.room.*
import com.example.slancho.db.model.ForecastInfo

@Dao
interface ForecastInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastInfo: ForecastInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastInfoList(vararg forecastInfoList: ForecastInfo)

//    @Transaction
//    fun insertForecastInfoList(forecastInfoList: List<ForecastInfo>) =
//        forecastInfoList.forEach { insert(it) }
//
//    @Query("SELECT * FROM forecastInfo WHERE forecastId =:forecastId")
//    fun getLatestForecastDataListByForecastId(forecastId: String): List<ForecastInfo>
}