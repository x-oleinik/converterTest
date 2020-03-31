package io.realmagic.convertertest

import android.app.Application
import io.realmagic.convertertest.remote.Requests
import io.realmagic.convertertest.remote.RestClient

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        restClient
    }

    companion object {
        val restClient = RestClient()
        val requests = Requests(restClient)

    }
}