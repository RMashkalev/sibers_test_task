package com.example.component.navigation.routers

import androidx.navigation.NavController
import com.example.features.feed.presentation.FeedRouter
import com.example.features.feed.ui.FeedFragmentDirections

class FeedRouterImpl(
	private val navController: NavController,
) : FeedRouter {

	override fun openDetails(name: String) {
		val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(name)
		navController.navigate(action)
	}
}