package io.realmagic.convertertest.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomRates::class), version = 1)
abstract class Database : RoomDatabase(){
    abstract fun getRateDao() : RateDao
}