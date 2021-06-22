package com.decagon.android.sq007.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.decagon.android.sq007.R
import kotlinx.android.synthetic.main.fragment_pokemon_profile.*
import java.util.*

class PokemonProfileFragment : Fragment() {

    val args: PokemonProfileFragmentArgs by navArgs()
    private lateinit var pokemonUrl: String

    private lateinit var pokemonGalleryViewModel: PokemonGalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemonGalleryViewModel = ViewModelProvider(this).get(PokemonGalleryViewModel::class.java)
        pokemonUrl = args.pokemonUrl
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonGalleryViewModel.getPokemonDetails(pokemonUrl).observe(
            viewLifecycleOwner,
            {

                /**
                 * Changing the images
                 * **/
                context?.let { it1 ->
                    Glide.with(it1)
                        .load(it.sprites.front_default)
                        .error(R.drawable.ic_baseline_insert_emoticon_24)
                        .fitCenter()
                        .transform(RoundedCorners(30))
                        .into(pokemon_image)
                }

                /***
                 * Populating the ability items
                 * */
                val adapter = AbilityAdapter(it.abilities)
                recycler_ability.layoutManager = LinearLayoutManager(context)
                recycler_ability.adapter = adapter

                /***
                 * Populating the move items
                 * */
                val moveAdapter = MoveAdapter(it.moves)
                recycler_moves.layoutManager = GridLayoutManager(context, 2)
                recycler_moves.adapter = moveAdapter

                /***
                 * Populating the stats items
                 * */
                val statsAdapter = StatsAdapter(it.stats)
                recycler_stat.layoutManager = LinearLayoutManager(context)
                recycler_stat.adapter = statsAdapter

                /***
                 * Populating the height and weight
                 * */
                height.text = it.height.toString()
                weight.text = it.weight.toString()
                name.text = it.name.capitalize(Locale.ROOT)
            }
        )
    }
}
