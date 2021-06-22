package com.decagon.android.sq007.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.model.Ability
import kotlinx.android.synthetic.main.list_items.view.*
import java.util.*

class AbilityAdapter(var abilityList: List<Ability>) : RecyclerView.Adapter<AbilityAdapter.PokemonDetailViewHolder>() {

    inner class PokemonDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bindText: TextView = itemView.list_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityAdapter.PokemonDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return PokemonDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbilityAdapter.PokemonDetailViewHolder, position: Int) {
        holder.bindText.text = abilityList[position].ability.name.capitalize(Locale.ROOT)
    }

    override fun getItemCount(): Int = abilityList.size
}
