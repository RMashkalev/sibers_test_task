package com.example.features.feed.data.repository

import com.example.features.feed.data.datasource.FeedRemoteDataSource
import com.example.features.feed.data.mapper.toEntity
import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.domain.repository.FeedRepository

class FeedRepositoryImpl(
	private val feedRemoteDataSource: FeedRemoteDataSource
) : FeedRepository {

	override suspend fun loadPokemons(limit: Int, offset: Int): List<BasePokemon> {
		val request = feedRemoteDataSource.loadPokemons(limit, offset)
		val result = request.map { it.toEntity() }
		return result
	}
}