package com.example.features.feed.data.model

data class PokemonNamesResponse(
	val results: List<Form>
)

data class BasePokemonModel(
	val forms: List<Form>,
	val stats: List<Stats>,
	val sprites: Sprites,
)

data class Form(
	val name: String,
	val url: String,
)

data class Stats(
	val base_stat: Int,
	val effort: Int,
	val stat: Stat
)

data class Stat(
	val name: String,
	val url: String,
)

data class Sprites(
	val front_default: String,
)