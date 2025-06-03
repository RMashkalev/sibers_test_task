package com.example.features.feed.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.features.feed.domain.entity.BasePokemon
import com.example.features.feed.presentation.FeedRouter
import com.example.features.feed.presentation.FeedState
import com.example.features.feed.presentation.FeedViewModel
import com.example.feed.R
import com.example.feed.databinding.FragmentFeedBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedFragment : Fragment() {

	private var _binding: FragmentFeedBinding? = null
	private val binding get() = _binding!!
	private val router: FeedRouter by inject { parametersOf(findNavController()) }
	private val viewModel: FeedViewModel by inject { parametersOf(router) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentFeedBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.pokemons.observe(viewLifecycleOwner) { pokemonList ->
			if (pokemonList.isNotEmpty()) {
				val firstPokemon = pokemonList[0]
				updateUI(firstPokemon)
			}
		}

		viewModel.load()
	}

	private fun updateUI(pokemon: BasePokemon) {
		binding.textView.text = pokemon.name ?: getString(R.string.empty_name)
		binding.textView2.text = pokemon.statAttack.toString()
		binding.textView3.text = pokemon.statDefense.toString()
		binding.textView4.text = pokemon.statHealth.toString()
		Log.d("aaa", pokemon.imgUrl.toString())
		binding.imageView.load(pokemon.imgUrl) {
			crossfade(true)
			placeholder(R.drawable.placeholder) // Ваша кастомная заглушка
			error(R.drawable.placeholder) // Картинка при ошибке
			lifecycle(viewLifecycleOwner)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}