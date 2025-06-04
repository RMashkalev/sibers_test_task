package com.example.features.details.domain.usecase

import com.example.features.details.domain.entity.PokemonDetails
import com.example.features.details.domain.repository.DetailsRepository

class LoadPokemonDataUseCase(
	private val repository: DetailsRepository,
) : suspend (String) -> PokemonDetails by repository::loadPokemonData