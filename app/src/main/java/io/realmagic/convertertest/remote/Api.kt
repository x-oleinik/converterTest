package io.realmagic.convertertest.remote

import android.telecom.Call
import io.realmagic.convertertest.Data.LatestData
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    companion object{
        const val BASE_URL = "https://api.exchangeratesapi.io/"
        const val LATEST = "latest"
        const val BASE = "base"
        const val SYMBOLS = "symbols"
        const val SHARED_PREF = "myPrefs"
        const val PREF_RATES = "rates"
    }

    @GET(LATEST)
    fun getLatest() : retrofit2.Call<LatestData>

    @GET(LATEST)
    fun getRate(@Query (BASE) base : String, @Query (SYMBOLS) symbols : String) : retrofit2.Call<LatestData>
}