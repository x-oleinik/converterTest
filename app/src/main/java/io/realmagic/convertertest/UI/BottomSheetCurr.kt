package io.realmagic.convertertest.UI

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.realmagic.convertertest.Adapters.CurrListAdapter
import io.realmagic.convertertest.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_l.*

class BottomSheetCurr(private val isFrom : Boolean, private val currencyList : ArrayList<String>) : BottomSheetDialogFragment(){

    private var fragmentView : View? = null


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

        val manager = LinearLayoutManager(context)
        val adapter = CurrListAdapter(currencyList)

        curr_rv.layoutManager =  manager
        curr_rv.adapter = adapter
        adapter.onItemClick = {
            Log.i("currency: ", it)
            if (isFrom) activity?.from?.text = it
            else activity?.to?.text = it
            this.dismiss()
        }

        search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })

    }

    private fun getScreenHeight(): Int{
        return Resources.getSystem().displayMetrics.heightPixels
    }

}