package com.example.features.feed.data.mapper

import com.example.features.feed.data.model.BasePokemonModel
import com.example.features.feed.domain.entity.BasePokemon

internal fun BasePokemonModel.toEntity(attack: Int, defense: Int, health: Int) = BasePokemon(
		name = forms.firstOrNull()?.name,
		imgUrl = sprites.front_default,
		statAttack = attack,
		statDefense = defense,
		statHealth = health,
	)
