/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.slancho.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.slancho.db.dao.*
import com.example.slancho.db.model.*

/**
 * Main database description.
 */
@Database(
    entities = [
        User::class,
        LastKnownLocation::class,
        Forecast::class,
        City::class,
        ForecastInfo::class
    ],
    version = 12,
    exportSchema = false
)
abstract class SlanchoDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun lastKnownLocationDao(): LastKnownLocationDao
    abstract fun forecastDao(): ForecastDao
    abstract fun cityDao(): CityDao
    abstract fun forecastInfoDao(): ForecastInfoDao
}

