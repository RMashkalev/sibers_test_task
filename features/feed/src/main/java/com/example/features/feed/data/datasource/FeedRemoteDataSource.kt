package com.example.features.feed.data.datasource

import com.example.features.feed.data.api.FeedApi
import com.example.features.feed.data.model.BasePokemonModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

interface FeedRemoteDataSource {

	suspend fun loadPokemons(limit: Int, offset: Int) : List<BasePokemonModel>
}

class FeedRemoteDataSourceImpl(
	private val feedApi: FeedApi,
) : FeedRemoteDataSource {

	override suspend fun loadPokemons(limit: Int, offset: Int): List<BasePokemonModel> {
		val names = feedApi.loadPokemonNames(limit, offset).results.map { it.name }

		return coroutineScope {
			names.map { name ->
				async {
					try {
						feedApi.loadPokemon(name)
					} catch (e: Exception) {
						null
					}
				}
			}.awaitAll().filterNotNull()
		}
	}
}