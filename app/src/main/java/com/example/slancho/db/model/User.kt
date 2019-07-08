package com.example.slancho.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Long?
) {

}