package com.example.features.feed.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.features.feed.presentation.FeedRouter
import com.example.feed.databinding.FragmentFeedBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedFragment : Fragment() {

	private var _binding: FragmentFeedBinding? = null
	private val binding get() = _binding!!
	private val navController: FeedRouter by inject { parametersOf(findNavController()) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentFeedBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.button.setOnClickListener {
			navController.openDetails()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}