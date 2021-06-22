package com.decagon.android.sq007.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.model.Move
import java.util.*

class MoveAdapter(var moveList: List<Move>) : RecyclerView.Adapter<MoveAdapter.MoveViewHolder>() {

    inner class MoveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moves: TextView = itemView.findViewById(R.id.list_item)

        fun bind(moveList: List<Move>, position: Int) {
            moves.text = moveList[position].move.name.capitalize(Locale.ROOT)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MoveViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(moveList, position)
    }

    override fun getItemCount(): Int = moveList.size
}
