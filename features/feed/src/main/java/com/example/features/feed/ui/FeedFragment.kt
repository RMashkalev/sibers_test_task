package com.example.features.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.feed.presentation.FeedRouter
import com.example.features.feed.presentation.FeedViewModel
import com.example.features.feed.presentation.PokemonRVAdapter
import com.example.feed.databinding.FragmentFeedBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedFragment : Fragment() {

	private var _binding: FragmentFeedBinding? = null
	private val binding get() = _binding!!
	private val router: FeedRouter by inject { parametersOf(findNavController()) }
	private val viewModel: FeedViewModel by inject { parametersOf(router) }

	private lateinit var adapter: PokemonRVAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentFeedBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		adapter = PokemonRVAdapter(
			onItemClick = {
				viewModel.openDetails(it)
			}
		)
		binding.recyclerView.adapter = adapter
		binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

		viewModel.pokemons.observe(viewLifecycleOwner) { pokemonList ->
			if (pokemonList.isNotEmpty()) {
				adapter.submitList(pokemonList)
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}