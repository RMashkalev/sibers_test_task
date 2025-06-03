package com.example.features.feed.domain.usecase

import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.domain.repository.FeedRepository

class LoadPokemonsUseCase(
	private val repository: FeedRepository
) : suspend (Int, Int) -> List<BasePokemon> by repository::loadPokemons