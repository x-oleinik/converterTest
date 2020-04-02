package io.realmagic.convertertest.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class LatestData(
    @SerializedName("rates")
    val rates : Map<String, Double>,

    @SerializedName("base")
    val base : String,

    @SerializedName("date")
    val date : String
)

