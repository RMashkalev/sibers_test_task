package com.example.features.feed.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.domain.usecase.LoadPokemonsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
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

	init {
		load()
	}

//	private val errorHandler = CoroutineExceptionHandler { _, exception ->
//		Log.e("FeedViewModel", "Error occurred: ${exception.localizedMessage}", exception)
//	}

	fun load() {
		if (isLoading || isLastPage) return

		viewModelScope.launch {
			val result = loadPokemonsUseCase(POKEMONS_ON_PAGE, offset)
			if (result.isNotEmpty()) {
				pokemons.value = pokemons.value.orEmpty() + result
				offset += POKEMONS_ON_PAGE
			} else {
				isLastPage = false
			}
		}
	}

	fun openDetails(name: String) {
		feedRouter.openDetails(name)
	}
}