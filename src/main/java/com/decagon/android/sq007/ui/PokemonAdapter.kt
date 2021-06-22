package com.decagon.android.sq007.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.decagon.android.sq007.R
import com.decagon.android.sq007.model.Result
import com.decagon.android.sq007.utils.PokemonCardClickListener
import com.decagon.android.sq007.utils.getPokemonNumber
import kotlinx.android.synthetic.main.pokemon_list.view.*
import java.util.*

class PokemonAdapter(
    var context: Context,
    private val pokemonList: List<Result>,
    private var listener: PokemonCardClickListener
) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    class PokemonHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var bindCard: CardView = itemView.card
        var bindTitle: TextView = itemView.pokemonName
        val bindDrawable: ImageView = itemView.pokemonImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        holder.bindTitle.text = pokemonList[position].name.capitalize(Locale.ROOT)
        val pokeNo = getPokemonNumber(pokemonList[position].url)
        // Using a default image till images are loaded from the URL
        Glide.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$pokeNo.png")
            .error(R.drawable.ic_baseline_insert_emoticon_24)
            .fitCenter()
            .transform(RoundedCorners(30))
            .into(holder.bindDrawable)

        holder.bindCard.setOnClickListener {
            val url = pokemonList[position].url
            listener.onCardClick(url)
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}
