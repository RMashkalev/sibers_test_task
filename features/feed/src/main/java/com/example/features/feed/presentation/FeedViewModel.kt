package com.example.features.feed.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.domain.usecase.LoadPokemonsUseCase
import com.example.navigation_contract.routers.FeedRouter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private const val POKEMONS_ON_PAGE = 30

class FeedViewModel(
	private val loadPokemonsUseCase: LoadPokemonsUseCase,
	private val feedRouter: FeedRouter,
) : ViewModel() {

	val pokemons = MutableLiveData<List<BasePokemon>>(listOf())
	var isLoading = false
	var isLastPage = false
	private var offset = 0

	private val _errorMessage = MutableStateFlow<String?>(null)
	val errorMessage: StateFlow<String?> = _errorMessage
	private val errorHandler = CoroutineExceptionHandler { _, exception ->
		val message = exception.message ?: "Неизвестная ошибка"
		_errorMessage.value = message
	}

	init {
		load()
	}

	fun load() {
		if (isLoading || isLastPage) return

		viewModelScope.launch(errorHandler) {
			val result = loadPokemonsUseCase(POKEMONS_ON_PAGE, offset)
			if (result.isNotEmpty()) {
				pokemons.value = pokemons.value.orEmpty() + result
				offset += POKEMONS_ON_PAGE
			} else {
				isLastPage = false
			}
		}
	}

	fun clearError() {
		_errorMessage.value = null
	}

	fun openDetails(name: String) {
		feedRouter.openDetails(name)
	}
}