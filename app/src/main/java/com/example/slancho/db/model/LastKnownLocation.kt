package com.example.slancho.db.model

import android.location.Address
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "lastKnownLocation")
data class LastKnownLocation(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: String,
    @ColumnInfo(name = "latitude")
    var latitude: Double,
    @ColumnInfo(name = "longitude")
    var longitude: Double,
    @ColumnInfo(name = "city")
    var city: String,
    @ColumnInfo(name = "country")
    var country: String,
    @ColumnInfo(name = "countryCode")
    var countryCode: String
) {
    constructor(userId: String, address: Address) : this(
        userId,
        address.latitude,
        address.longitude,
        address.locality,
        address.countryName,
        address.countryCode
    )

    @Ignore
    fun getFormattedLocation(): String = "${this.city},${this.country}"
}
