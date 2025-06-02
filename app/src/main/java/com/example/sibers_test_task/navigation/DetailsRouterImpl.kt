package com.example.sibers_test_task.navigation

import androidx.navigation.NavController
import com.example.features.details.presentation.DetailsRouter
import com.example.sibers_test_task.R

class DetailsRouterImpl(
	private val navController: NavController,
) : DetailsRouter {

	override fun backToFeed() {
		navController.navigate(R.id.action_detailFragment_to_feedFragment)
	}
}