package com.example.slancho.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.slancho.db.model.LastKnownLocation

@Dao
interface LastKnownLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lastKnownLocation: LastKnownLocation)

    @Query("SELECT * FROM lastKnownLocation WHERE userId =:userId")
    fun getLastKnownLocationForUserByUserId(userId: String): LastKnownLocation
}