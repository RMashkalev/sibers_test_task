package com.example.features.feed.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation_contract.routers.FeedRouter
import com.example.features.feed.presentation.FeedViewModel
import com.example.features.feed.presentation.PokemonRVAdapter
import com.example.feed.databinding.FragmentFeedBinding
import kotlinx.coroutines.launch
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
		Log.d("aaa", "onViewCreated")
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

		lifecycleScope.launch {
			viewModel.errorMessage.collect { message ->
				if (message != null) {
					Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
					binding.reloadButton.visibility = View.VISIBLE
					viewModel.clearError()
				}
			}
		}

		binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
				super.onScrolled(recyclerView, dx, dy)

				val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return

				val visibleItemCount = layoutManager.childCount
				val totalItemCount = layoutManager.itemCount
				val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

				val isAtEnd = (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
				val shouldPaginate = isAtEnd && firstVisibleItemPosition >= 0

				if (shouldPaginate && !viewModel.isLoading && !viewModel.isLastPage) {
					viewModel.load()
				}
			}
		})

		binding.reloadButton.setOnClickListener {
			viewModel.load()
			binding.reloadButton.visibility = View.GONE
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
		Log.d("aaa", "onViewDestroy")
	}
}