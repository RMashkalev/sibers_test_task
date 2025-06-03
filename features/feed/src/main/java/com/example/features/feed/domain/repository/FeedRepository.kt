package com.example.features.feed.domain.repository

import com.example.features.feed.domain.entity.BasePokemon

interface FeedRepository {

	suspend fun loadPokemons(limit: Int, offset: Int) : List<BasePokemon>
}