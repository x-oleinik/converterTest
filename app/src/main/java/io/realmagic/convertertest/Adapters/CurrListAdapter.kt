package io.realmagic.convertertest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realmagic.convertertest.R
import kotlinx.android.synthetic.main.curr_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CurrListAdapter(private val currList: ArrayList<String>) :
    RecyclerView.Adapter<CurrListAdapter.CurrListViewHolder>(), Filterable {

    private var filteredlist = currList


    var onItemClick : ((String) -> Unit)? = null

    override fun getItemCount(): Int = filteredlist.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrListAdapter.CurrListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.curr_item, parent, false)
        return CurrListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrListAdapter.CurrListViewHolder, position: Int) {
        holder.name.text = filteredlist.get(position)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.isNullOrEmpty()){
                    filteredlist = currList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in currList){
                        if (row.toLowerCase(Locale.ROOT).contains(p0.toString().toLowerCase(Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    filteredlist = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredlist
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filteredlist = p1?.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }


    inner class CurrListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView

        init {
            name = itemView.findViewById(R.id.curr_name)
            itemView.setOnClickListener {
                onItemClick?.invoke(filteredlist[adapterPosition])
            }
        }
    }
}