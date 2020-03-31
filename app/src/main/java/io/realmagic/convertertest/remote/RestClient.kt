package io.realmagic.convertertest.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestClient {

    lateinit var api: Api
    private val gson = GsonConverterFactory.create(GsonBuilder().create())
    private var okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        //Log everything
        addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
    }.build()

    init {
        setRetrofitClient()
    }


    private fun setRetrofitClient(){
        api = Retrofit.Builder().apply {
            client(okHttpClient)
            baseUrl(Api.BASE_URL)
            addConverterFactory(gson)
        }.build().create(Api::class.java)
    }

}