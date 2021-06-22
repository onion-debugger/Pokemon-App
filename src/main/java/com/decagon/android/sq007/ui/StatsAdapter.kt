package com.decagon.android.sq007.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.model.Stat
import java.util.*

class StatsAdapter(var statsList: List<Stat>) : RecyclerView.Adapter<StatsAdapter.StatsViewHolder>() {

    inner class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stat: TextView = itemView.findViewById(R.id.list_item)

        fun bind(statsList: List<Stat>, position: Int) {
            stat.text = statsList[position].stat.name.capitalize(Locale.ROOT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.StatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return StatsViewHolder(view)
    }

    override fun getItemCount(): Int = statsList.size

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(statsList, position)
    }
}
