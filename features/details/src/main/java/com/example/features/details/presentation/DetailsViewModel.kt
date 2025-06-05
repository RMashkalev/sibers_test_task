package com.example.features.details.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.details.domain.entity.PokemonDetails
import com.example.features.details.domain.usecase.LoadPokemonDataUseCase
import com.example.navigation_contract.routers.DetailsRouter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
	private val loadPokemonDataUseCase: LoadPokemonDataUseCase,
	private val detailsRouter: DetailsRouter,
) : ViewModel() {

	val pokemon = MutableLiveData<PokemonDetails>()

	private val _errorMessage = MutableStateFlow<String?>(null)
	val errorMessage: StateFlow<String?> = _errorMessage
	private val errorHandler = CoroutineExceptionHandler { _, exception ->
		val message = exception.message ?: "Неизвестная ошибка"
		_errorMessage.value = message
	}

	fun load(name: String) {
		viewModelScope.launch(errorHandler) {
			pokemon.value = loadPokemonDataUseCase(name)
		}
	}

	fun backToFeed() {
		detailsRouter.backToFeed()
	}

	fun clearError() {
		_errorMessage.value = null
	}
}