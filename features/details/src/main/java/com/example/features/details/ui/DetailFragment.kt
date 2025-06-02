package com.example.features.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.details.databinding.FragmentDetailBinding
import com.example.features.details.presentation.DetailsRouter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailFragment : Fragment() {

	private var _binding: FragmentDetailBinding? = null
	private val binding get() = _binding!!
	private val navController: DetailsRouter by inject { parametersOf(findNavController()) }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentDetailBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.button.setOnClickListener {
			navController.backToFeed()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}