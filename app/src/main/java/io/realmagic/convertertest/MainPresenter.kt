package io.realmagic.convertertest

import android.content.Context
import android.content.DialogInterface
import android.net.*
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.realmagic.convertertest.Data.LatestData
import io.realmagic.convertertest.Data.RoomRates
import io.realmagic.convertertest.remote.Api
import java.lang.reflect.Type
import java.net.InetAddress
import java.net.UnknownHostException
import java.nio.channels.FileLock
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(view: MainContract.View, ctx: Context) : MainContract.Presenter {

    val mView = view
    val mCtx = ctx

    override fun getRatesToDb() {
        if (checkConnection()) {
            mView.startAnimate()
            App.requests.getLatest {
                fillDb(it)
                mView.stopAnimate()
            }
        } else mView.errorDialog(mCtx.getString(R.string.no_connection))
    }

    override fun getList(isFrom: Boolean) {
        val nameList = ArrayList<String>()
        val list = App.database?.getRateDao()?.getAll()
        if (list != null)
            for (i in 0 until list.size) {
                nameList.add(list[i].name)
            }
        mView.showBottomSheet(isFrom, nameList)
    }

    override fun countFromBase(from: String, to: String, amount: Double) {
        val rateFrom = App.database?.getRateDao()?.get(from)?.rate
        val rateTo = App.database?.getRateDao()?.get(to)?.rate

        if (rateFrom != null && rateTo != null) {
            val rate = rateTo / rateFrom
            val result = rate * amount
            mView.updateAmount(numbers(result))
        }
    }

    private fun checkConnection(): Boolean {
        val cm = mCtx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    private fun fillDb(data: LatestData) {
        val currencyList = ArrayList<String>()
        val rateList = ArrayList<Double>()
        val roomRates = ArrayList<RoomRates>()

        currencyList.add(data.base)
        currencyList.addAll(data.rates.keys)

        rateList.add(1.0)
        rateList.addAll(data.rates.values)

        for (i in 0 until currencyList.size) {
            roomRates.add(RoomRates(currencyList[i], rateList[i]))
        }

        //fill or update
        if (App.database?.getRateDao()?.getAll().isNullOrEmpty())
            App.database?.getRateDao()?.addAll(roomRates)
        else App.database?.getRateDao()?.updateAll(roomRates)


        val rates = App.database?.getRateDao()?.getAll()
        Log.i("rates: ", rates.toString())

    }

    private fun numbers(double: Double): String {

        val locale = Locale.UK

        val numberFormat = NumberFormat.getNumberInstance(locale)
        numberFormat.maximumFractionDigits = 4
        numberFormat.minimumFractionDigits = 2

        return numberFormat.format(double)

    }


}