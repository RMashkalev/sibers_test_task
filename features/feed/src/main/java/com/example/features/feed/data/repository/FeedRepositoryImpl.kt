package com.example.features.feed.data.repository

import android.util.Log
import com.example.features.feed.data.datasource.FeedRemoteDataSource
import com.example.features.feed.data.mapper.toEntity
import com.example.features.feed.data.model.StatsEnum
import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.domain.repository.FeedRepository

class FeedRepositoryImpl(
	private val feedRemoteDataSource: FeedRemoteDataSource
) : FeedRepository {

	override suspend fun loadPokemons(limit: Int, offset: Int): List<BasePokemon> {
		val request = feedRemoteDataSource.loadPokemons(limit, offset)
		val result = request.map {
			val attack = it.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.ATTACK.statName }?.base_stat ?: 0
			val defense = it.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.DEFENSE.statName }?.base_stat ?: 0
			val health = it.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.HEALTH.statName }?.base_stat ?: 0
			it.toEntity(attack, defense, health)
		}
		return result
	}
}