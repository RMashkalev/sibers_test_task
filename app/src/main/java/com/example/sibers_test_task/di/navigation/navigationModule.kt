package com.example.sibers_test_task.di.navigation

import androidx.navigation.NavController
import com.example.features.details.presentation.DetailsRouter
import com.example.features.feed.presentation.FeedRouter
import com.example.sibers_test_task.navigation.DetailsRouterImpl
import com.example.sibers_test_task.navigation.FeedRouterImpl
import org.koin.dsl.module

val navigationModule = module {

	single<FeedRouter> { (navController: NavController) ->
		FeedRouterImpl(navController)
	}

	single<DetailsRouter> { (navController: NavController) ->
		DetailsRouterImpl(navController)
	}
}