package io.realmagic.convertertest

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.realmagic.convertertest.remote.Api
import java.lang.reflect.Type
import java.nio.channels.FileLock

class MainPresenter (view : MainContract.View, ctx : Context) : MainContract.Presenter{

    val mView = view
    val mCtx = ctx

    override fun getRates() {
        App.requests.getLatest {
            saveToSharedPref(it.rates)
        }
    }

    override fun countRate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun saveToSharedPref(rateMap : Map<String, Float>){
        val sharedPrefs = mCtx.getSharedPreferences(Api.SHARED_PREF, Context.MODE_PRIVATE)
        val gson = Gson()
        val rateMapString = gson.toJson(rateMap)

        sharedPrefs.edit().putString(Api.PREF_RATES, rateMapString).apply()
    }

    private fun getFromPrefs() : Map<String, Float>{
        val sharedPrefs = mCtx.getSharedPreferences(Api.SHARED_PREF, Context.MODE_PRIVATE)
        val gson = Gson()

        val stringFromPrefs = sharedPrefs.getString(Api.PREF_RATES, "empty")
        val turnsType = object : TypeToken<Map<String, Float>>() {}.type
        val mapFromPrefs = gson.fromJson<Map<String, Float>>(stringFromPrefs, turnsType)

        return mapFromPrefs

    }



}