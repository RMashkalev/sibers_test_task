package com.example.features.feed.data.api

import com.example.features.feed.data.model.BasePokemonModel
import com.example.features.feed.data.model.PokemonNamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FeedApi {

	@GET("pokemon")
	suspend fun loadPokemonNames(@Query("limit") limit: Int, @Query("offset") offset: Int) : PokemonNamesResponse

	@GET("pokemon/{name}")
	suspend fun loadPokemons(@Path("name") name: String) : BasePokemonModel
}