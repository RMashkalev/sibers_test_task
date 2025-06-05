package com.example.features.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.details.R
import com.example.details.databinding.FragmentDetailBinding
import com.example.navigation_contract.routers.DetailsRouter
import com.example.features.details.presentation.DetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import coil.load
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

	private var _binding: FragmentDetailBinding? = null
	private val binding get() = _binding!!
	private val router: DetailsRouter by inject { parametersOf(findNavController()) }
	private val viewModel: DetailsViewModel by inject { parametersOf(router) }
	private val name: DetailFragmentArgs by navArgs()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentDetailBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.load(name.pokemonName)

		viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
			if (pokemon != null) {
				binding.pokemonName.text = name.pokemonName
				binding.statAtk.text = pokemon.statAtk.toString()
				binding.statDef.text = pokemon.statDef.toString()
				binding.statHp.text = pokemon.statHp.toString()
				binding.statHeight.text = pokemon.statHeight.toString()
				binding.statWeight.text = pokemon.statWeight.toString()
				binding.pokemonGenerations.text = pokemon.generations.toString()
				binding.imgPokemon.load(pokemon.imgUrl) {
					placeholder(R.drawable.pokemon_load)
					error(R.drawable.placeholder)
				}
			}
		}

		binding.backButton.setOnClickListener {
			router.backToFeed()
		}

		lifecycleScope.launch {
			viewModel.errorMessage.collect { message ->
				if (message != null) {
					Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
					viewModel.clearError()
				}
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}