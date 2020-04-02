package io.realmagic.convertertest.Data

import androidx.room.*

@Dao
interface RateDao {
    @Insert
    fun addAll(rates : List<RoomRates>)

    @Insert
    fun add(rate : RoomRates)

    @Update
    fun updateAll(rates: List<RoomRates>)

    @Query("SELECT * FROM latestRates WHERE name LIKE :name")
    fun get(name : String) : RoomRates

    @Query("SELECT * FROM latestRates")
    fun getAll() : List<RoomRates>


}