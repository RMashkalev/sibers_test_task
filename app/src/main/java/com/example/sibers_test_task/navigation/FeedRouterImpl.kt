package com.example.sibers_test_task.navigation

import androidx.navigation.NavController
import com.example.features.feed.presentation.FeedRouter
import com.example.sibers_test_task.R

class FeedRouterImpl(
	private val navController: NavController,
) : FeedRouter {

	override fun openDetails() {
		navController.navigate(R.id.action_feedFragment_to_detailFragment)
	}
}