package io.realmagic.convertertest.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realmagic.convertertest.Adapters.CurrListAdapter
import io.realmagic.convertertest.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_l.*

class BottomSheetCurr : BottomSheetDialogFragment(){

    private var fragmentView : View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.bottom_sheet_l, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf("usd", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur", "eur")
        val manager = LinearLayoutManager(context)
        val adapter = CurrListAdapter(list)

        curr_rv.layoutManager =  manager
        curr_rv.adapter = adapter
        adapter.onItemClick = {
            Log.i("currency: ", it)
            this.dismiss()
        }
    }

}