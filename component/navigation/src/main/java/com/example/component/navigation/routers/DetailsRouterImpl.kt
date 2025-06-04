package com.example.component.navigation.routers

import androidx.navigation.NavController
import com.example.component.navigation.R
import com.example.features.details.presentation.DetailsRouter

class DetailsRouterImpl(
	private val navController: NavController,
) : DetailsRouter {

	override fun backToFeed() {
		navController.navigate(R.id.action_detailFragment_to_feedFragment)
	}
}