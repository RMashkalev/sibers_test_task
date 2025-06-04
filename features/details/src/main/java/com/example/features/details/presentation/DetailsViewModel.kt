package com.example.features.details.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.details.domain.entity.PokemonDetails
import com.example.features.details.domain.usecase.LoadPokemonDataUseCase
import kotlinx.coroutines.launch

class DetailsViewModel(
	private val loadPokemonDataUseCase: LoadPokemonDataUseCase,
	private val detailsRouter: DetailsRouter,
) : ViewModel() {

	val pokemon = MutableLiveData<PokemonDetails>()

	fun load(name: String) {
		viewModelScope.launch {
			pokemon.value = loadPokemonDataUseCase(name)
		}
	}
}