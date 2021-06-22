package com.decagon.android.sq007

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.decagon.android.sq007.ui.PokemonAdapter
import com.decagon.android.sq007.ui.PokemonGalleryViewModel
import com.decagon.android.sq007.utils.NetworkLiveData
import com.decagon.android.sq007.utils.PokemonCardClickListener
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

private const val TAG = "PokemonGalleryFragment"

class PokemonGalleryFragment : Fragment(), PokemonCardClickListener {

    private lateinit var pokemonGalleryViewModel: PokemonGalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemonGalleryViewModel = ViewModelProvider(this).get(PokemonGalleryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    // Observing the viewLiveData
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NetworkLiveData.observe(
            viewLifecycleOwner,
            Observer {
                if (it) {
                    pokemonGalleryViewModel.getPokemons()
                    Toast.makeText(requireContext(), "Connection status: $it", Toast.LENGTH_LONG).show()

                    pokemonGalleryViewModel.pokemonListLiveData.observe(
                        viewLifecycleOwner,
                        Observer { pokemons ->
                            pokemonPhotoRecyclerView.layoutManager = GridLayoutManager(context, 2)
                            pokemonPhotoRecyclerView.adapter =
                                PokemonAdapter(requireContext(), pokemons.results, this)
                        }
                    )
                } else {
                    Toast.makeText(requireContext(), "Connection is gone: $it", Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    /**
     * Navigating to the Detail Page using NavController, Actions and Args
     * */
    override fun onCardClick(url: String) {
        val action = PokemonGalleryFragmentDirections.actionPokemonGalleryFragmentToPokemonProfileFragment(url)
        findNavController().navigate(action)
    }
}
