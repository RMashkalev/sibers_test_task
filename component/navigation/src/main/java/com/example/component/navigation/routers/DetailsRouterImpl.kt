package com.example.component.navigation.routers

import androidx.navigation.NavController
import com.example.navigation_contract.routers.DetailsRouter
import com.example.features.details.ui.DetailFragmentDirections

class DetailsRouterImpl(
	private val navController: NavController,
) : DetailsRouter {

	override fun backToFeed() {
		val action = DetailFragmentDirections.actionDetailFragmentToFeedFragment()
		navController.navigate(action)
	}
}