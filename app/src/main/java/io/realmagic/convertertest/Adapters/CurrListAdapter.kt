package io.realmagic.convertertest.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realmagic.convertertest.R
import kotlinx.android.synthetic.main.curr_item.view.*

class CurrListAdapter(private val currList: ArrayList<String>) :
    RecyclerView.Adapter<CurrListAdapter.CurrListViewHolder>() {

    var onItemClick : ((String) -> Unit)? = null

    override fun getItemCount(): Int = currList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrListAdapter.CurrListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.curr_item, parent, false)
        return CurrListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrListAdapter.CurrListViewHolder, position: Int) {
        holder.name.text = currList.get(position)
    }

    inner class CurrListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView

        init {
            name = itemView.findViewById(R.id.curr_name)
            itemView.setOnClickListener {
                onItemClick?.invoke(currList[adapterPosition])
            }
        }
    }
}