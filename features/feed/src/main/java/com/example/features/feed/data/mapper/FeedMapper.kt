package com.example.features.feed.data.mapper

import com.example.features.feed.data.model.BasePokemonModel
import com.example.features.feed.data.model.StatsEnum
import com.example.features.feed.domain.entity.BasePokemon

internal fun BasePokemonModel.toEntity() = BasePokemon(
		name = forms.firstOrNull()?.name,
		imgUrl = sprites.front_default,
		statAttack = this.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.ATTACK.statName }?.base_stat ?: 0,
		statDefense = this.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.DEFENSE.statName }?.base_stat ?: 0,
		statHealth = this.stats.firstOrNull { iterator -> iterator.stat.name == StatsEnum.HEALTH.statName }?.base_stat ?: 0,
	)
