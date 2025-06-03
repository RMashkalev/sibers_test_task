package com.example.features.feed.data.datasource

import com.example.features.feed.data.api.FeedApi
import com.example.features.feed.data.model.BasePokemonModel

interface FeedRemoteDataSource {

	suspend fun loadPokemons(limit: Int, offset: Int) : List<BasePokemonModel>
}

class FeedRemoteDataSourceImpl(
	private val feedApi: FeedApi,
) : FeedRemoteDataSource {

	override suspend fun loadPokemons(limit: Int, offset: Int) : List<BasePokemonModel> {
		val names = feedApi.loadPokemonNames(limit, offset).results.map { it.name }
		val result = names.map {
			feedApi.loadPokemons(it)
		}
		return result
	}
}