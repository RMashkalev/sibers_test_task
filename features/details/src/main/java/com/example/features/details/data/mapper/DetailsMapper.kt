package com.example.features.details.data.mapper

import com.example.features.details.data.model.PokemonDetailsModel
import com.example.features.details.data.model.StatsEnum
import com.example.features.details.domain.entity.PokemonDetails

internal fun PokemonDetailsModel.toEntity() = PokemonDetails(
	imgUrl = sprites.front_default,
	statAtk = this.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.ATTACK.statName }?.base_stat ?: 0,
	statDef = this.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.DEFENSE.statName }?.base_stat ?: 0,
	statHp = this.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.HEALTH.statName }?.base_stat ?: 0,
	statHeight = this.height,
	statWeight = this.weight,
	generations = this.types.map { iterator -> iterator.type.name }
)