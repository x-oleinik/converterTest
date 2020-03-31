package io.realmagic.convertertest.Data

import com.google.gson.annotations.SerializedName

data class LatestData(
    @SerializedName("rates")
    val rates : Map<String, Float>,

    @SerializedName("base")
    val base : String,

    @SerializedName("date")
    val date : String
)