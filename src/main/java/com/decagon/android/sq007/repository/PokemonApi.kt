package com.decagon.android.sq007.repository

import com.decagon.android.sq007.model.Pokemon
import com.decagon.android.sq007.model.Pokemons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApi {
    // Using the HTTP request verb action
    @GET("pokemon?limit=100&offset=0")
    fun fetchContent(): Call<Pokemons>

    // get specific url of each pokemon
    @GET
    fun getPokemonDetails(@Url url: String): Call<Pokemon>
}
