package com.decagon.android.sq007.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.decagon.android.sq007.model.Pokemon
import com.decagon.android.sq007.model.Pokemons
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Pokemon"

class PokemonFetcher {

    private val pokemon: PokemonApi

    init {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(logging).build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()

        pokemon = retrofit.create(PokemonApi::class.java)
    }

    fun fetchContent(): LiveData<Pokemons> {
        val responseLiveData = MutableLiveData<Pokemons>()
        val pokemonRequest = pokemon.fetchContent()

        pokemonRequest.enqueue(object : Callback<Pokemons> {
            override fun onResponse(call: Call<Pokemons>, response: Response<Pokemons>) {
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Pokemons>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Photos", t)
            }
        })

        return responseLiveData
    }

    fun fetchPokemonDetails(url: String): LiveData<Pokemon> {
        val pokeResponseLiveData = MutableLiveData<Pokemon>()
        val pokemonRequest = pokemon.getPokemonDetails(url)

        pokemonRequest.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                pokeResponseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Photos", t)
            }
        })

        return pokeResponseLiveData
    }
}
