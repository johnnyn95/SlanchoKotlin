package com.example.slancho.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.slancho.db.model.City
import org.jetbrains.annotations.NotNull

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @Query("SELECT * FROM city WHERE name =:name")
    fun getCityByCityName(name: String): City

    @Query("SELECT * FROM city WHERE latitude =:lat AND longitude = :lon")
    fun getCityByLatLong(lat: Double, lon: Double): City
}