package com.example.features.feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.domain.usecase.LoadPokemonsUseCase
import kotlinx.coroutines.launch

private const val POKEMONS_ON_PAGE = 30

class FeedViewModel(
	private val loadPokemonsUseCase: LoadPokemonsUseCase,
	private val feedRouter: FeedRouter,
) : ViewModel() {

	val pokemons = MutableLiveData<List<BasePokemon>>(listOf())
	private var offset = 0
	private var continueLoad = true

	init {
		load()
	}

	private fun load() {
		if (!continueLoad) return

		viewModelScope.launch {
			val result = loadPokemonsUseCase(POKEMONS_ON_PAGE, offset)
			if (result.isNotEmpty()) {
				pokemons.value = pokemons.value.orEmpty() + result
				offset += POKEMONS_ON_PAGE
			} else {
				continueLoad = false
			}
		}
	}
}