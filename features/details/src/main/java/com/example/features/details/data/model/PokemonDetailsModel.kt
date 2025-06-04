package com.example.features.details.data.model

data class PokemonDetailsModel(
	val forms: List<Form>,
	val stats: List<Stats>,
	val types: List<Types>,
	val sprites: Sprites,
	val height: Int,
	val weight: Int,
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

data class Types(
	val type: Type,
)

data class Type(
	val name: String,
)

data class Sprites(
	val front_default: String,
)