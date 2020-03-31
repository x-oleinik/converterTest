package io.realmagic.convertertest.remote

import io.realmagic.convertertest.Data.LatestData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Requests (private val restClient: RestClient){


    fun getLatest(callback : (LatestData) -> Unit){
        restClient.api.getLatest().enqueue(getTileCallback<LatestData>(callback, {}))
    }

    fun getRate(from : String, to : String, callback: (LatestData) -> Unit){
        restClient.api.getRate(from, to).enqueue(getTileCallback<LatestData>(callback, {}))
    }


    private fun <T> getTileCallback(callback: (T) -> Unit, errorHandler: ((errorBody: String) -> Unit)? = null): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful)
                    response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                call.cancel()
            }
        }
    }
}