package com.example.slancho.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user")
data class User constructor(
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @PrimaryKey
    @ColumnInfo(name = "authUID")
    var authUID: String,
    @ColumnInfo(name = "isAnonymous")
    var isAnonymous: Boolean
) {
    @Ignore
    var lastKnownLocation: LastKnownLocation? = null

}