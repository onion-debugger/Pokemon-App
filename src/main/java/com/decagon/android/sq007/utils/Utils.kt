package com.decagon.android.sq007.utils

fun getPokemonNumber(url: String): Int {
    return url.split("https://pokeapi.co/api/v2/pokemon/")[1].split("/")[0].toInt()
}
