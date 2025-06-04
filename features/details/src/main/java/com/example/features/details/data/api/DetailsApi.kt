package com.example.features.details.data.api

import com.example.features.details.data.model.PokemonDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

	@GET("pokemon/{name}")
	suspend fun loadPokemonDetails(@Path("name") name: String) : PokemonDetailsModel
}