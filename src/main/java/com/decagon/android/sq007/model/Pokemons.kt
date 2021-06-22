package com.decagon.android.sq007.model

data class Pokemons(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)
