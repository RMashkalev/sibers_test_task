package com.example.features.details.domain.entity

data class PokemonDetails(
	val imgUrl: String,
	val statAtk: Int,
	val statDef: Int,
	val statHp: Int,
	val statHeight: Int,
	val statWeight: Int,
	val generations: List<String>
)