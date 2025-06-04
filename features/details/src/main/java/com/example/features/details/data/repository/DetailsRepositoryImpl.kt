package com.example.features.details.data.repository

import com.example.features.details.data.datasource.DetailsRemoteDataSource
import com.example.features.details.data.mapper.toEntity
import com.example.features.details.domain.entity.PokemonDetails
import com.example.features.details.domain.repository.DetailsRepository

class DetailsRepositoryImpl(
	private val detailsRemoteDataSource: DetailsRemoteDataSource,
) : DetailsRepository {

	override suspend fun loadPokemonData(name: String) : PokemonDetails {
		return detailsRemoteDataSource.loadPokemonData(name).toEntity()
	}
}