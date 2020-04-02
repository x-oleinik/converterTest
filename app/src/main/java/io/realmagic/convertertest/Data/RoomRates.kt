package io.realmagic.convertertest.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latestRates")
data class RoomRates(
    @PrimaryKey
    val name : String,

    @ColumnInfo(name = "rate")
    val rate : Double


)