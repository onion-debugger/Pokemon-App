package com.decagon.android.sq007.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decagon.android.sq007.model.Pokemon
import com.decagon.android.sq007.model.Pokemons
import com.decagon.android.sq007.repository.PokemonFetcher

class PokemonGalleryViewModel : ViewModel() {
    private var _pokemons = MutableLiveData<Pokemons>()
    val pokemonListLiveData: LiveData<Pokemons> get() = _pokemons

    fun getPokemonDetails(url: String): LiveData<Pokemon> {
        return PokemonFetcher().fetchPokemonDetails(url)
    }

    fun getPokemons() {
        _pokemons = PokemonFetcher().fetchContent() as MutableLiveData<Pokemons>
    }
}
