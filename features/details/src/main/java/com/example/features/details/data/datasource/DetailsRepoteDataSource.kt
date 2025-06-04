package com.example.features.details.data.datasource

import com.example.features.details.data.api.DetailsApi
import com.example.features.details.data.model.PokemonDetailsModel

interface DetailsRemoteDataSource {

	suspend fun loadPokemonData(name: String) : PokemonDetailsModel
}

class DetailsRemoteDataSourceImpl(
	private val detailsApi: DetailsApi,
) : DetailsRemoteDataSource {

	override suspend fun loadPokemonData(name: String) : PokemonDetailsModel {
		return detailsApi.loadPokemonDetails(name)
	}
}