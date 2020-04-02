package io.realmagic.convertertest

import android.app.Application
import androidx.room.Room
import io.realmagic.convertertest.Data.Database
import io.realmagic.convertertest.remote.Requests
import io.realmagic.convertertest.remote.RestClient

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        restClient
        database = Room.databaseBuilder(this, Database::class.java, "db")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        val restClient = RestClient()
        val requests = Requests(restClient)
        var database : Database? = null

    }
}