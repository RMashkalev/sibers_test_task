package com.example.features.details.domain.repository

import com.example.features.details.domain.entity.PokemonDetails

interface DetailsRepository {

	suspend fun loadPokemonData(name: String) : PokemonDetails
}